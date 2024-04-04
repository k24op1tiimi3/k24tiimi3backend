package k24.tiimi3.dogbackend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import k24.tiimi3.dogbackend.domain.CategoryRepository;
import k24.tiimi3.dogbackend.domain.Dog;
import k24.tiimi3.dogbackend.domain.DogRepository;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class DogController {
    @Autowired
    private DogRepository dogRepo;
    private CategoryRepository categoryRepo;

    @GetMapping("/clothesList")
    public String GetDog(Model model) {
        model.addAttribute("", dogRepo.findAll());
        return "clothesList";
    }

    @GetMapping("/addClothes")
    public String AddNewClothing(Model model) {
        model.addAttribute("clothing", new Dog());
        //model.addAttribute("categories", categoryRepo.findAll());
        return "addClothes";
    }

    @PostMapping("/saveClothing")
    public String SaveClothing(@ModelAttribute Dog dog) {
        // TODO: process POST request
        dogRepo.save(dog);
        return "redirect:/clothesList";
    }

    @GetMapping("/deleteClothing/{id}")
    public String DeleteClothing(@PathVariable("id") Long dogId) {
        dogRepo.deleteById(dogId);
        return "redirect:/clothesList";
    }

}
