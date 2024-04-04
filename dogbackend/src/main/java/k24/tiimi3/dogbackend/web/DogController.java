package k24.tiimi3.dogbackend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import k24.tiimi3.dogbackend.domain.DogRepository;
import k24.tiimi3.dogbackend.domain.DogRepository;

import org.springframework.ui.Model;

@Controller
public class DogController {
    @Autowired
    private DogRepository dogRepo;

    @GetMapping("/*")
    public String GetDog(Model model) {
        model.addAttribute();
        return "";
    }

}
