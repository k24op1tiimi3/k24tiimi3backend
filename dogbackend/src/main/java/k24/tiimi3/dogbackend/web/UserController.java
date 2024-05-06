package k24.tiimi3.dogbackend.web;

import k24.tiimi3.dogbackend.domain.AppUser;
import k24.tiimi3.dogbackend.domain.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/userlist")
    public String GetUsers(Model model) {
        model.addAttribute("users", appUserRepository.findAll());
        return "userlist";
    }

    @GetMapping("/addUser")
    public String AddUser(Model model) {
        model.addAttribute("user", new AppUser());
        return "addUser";
    }

    @PostMapping("/saveUser")
    public String SaveUser(@ModelAttribute AppUser user, Model model) {
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("errorMessage", "Passwords do not match.");
            return "addUser";
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        appUserRepository.save(user);
        System.out.println("Fetch all users:");
        for (AppUser appUser : appUserRepository.findAll()) {
            System.out.println(appUser.toString());
        }
        return "redirect:/userlist";
    }

    @GetMapping("/editUser/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editUser(@PathVariable("id") Long userId, Model model) {
        AppUser user = appUserRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + userId));
        model.addAttribute("user", user);
        return "editUser";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {
        appUserRepository.deleteById(userId);
        return "redirect:/userlist";
    }
}