package k24.tiimi3.dogbackend.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DogController {

    @GetMapping("/*")
    public String GetDog() {
        return "";
    }

}
