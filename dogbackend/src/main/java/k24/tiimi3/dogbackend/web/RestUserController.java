package k24.tiimi3.dogbackend.web;

import k24.tiimi3.dogbackend.domain.AppUser;
import k24.tiimi3.dogbackend.domain.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class RestUserController {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/appusers")
    public Iterable<AppUser> getMethodName() {
        return userRepository.findAll();
    }

    @PostMapping("/appusers")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser registerUser(@RequestBody AppUser newUser) {
        // if (!newUser.getPassword().equals(newUser.getPasswordConfirm())) {
        //     throw new IllegalArgumentException("Passwords do not match.");
        // }
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }
}