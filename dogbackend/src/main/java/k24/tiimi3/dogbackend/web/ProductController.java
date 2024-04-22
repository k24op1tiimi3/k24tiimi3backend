package k24.tiimi3.dogbackend.web;

import k24.tiimi3.dogbackend.domain.CategoryRepository;
import k24.tiimi3.dogbackend.domain.Product;
import k24.tiimi3.dogbackend.domain.ProductRepository;
import k24.tiimi3.dogbackend.domain.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository dogRepo;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private ManufacturerRepository manufacterRepo;

    @GetMapping("/index")
    public String GetIndex(Model model) {
        model.addAttribute("dogs", dogRepo.findAll());
        return "index";
    }

    @GetMapping("/clothesList")
    public String getClothes(Model model) {
        model.addAttribute("dogs", dogRepo.findAll());
        return "clothesList";
    }

    @GetMapping("/addClothes")
    public String AddNewClothing(Model model) {
        model.addAttribute("clothing", new Product());
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("manufacturers", manufacterRepo.findAll());
        return "addClothes";
    }

    @PostMapping("/saveClothing")
    public String SaveClothing(@ModelAttribute Product dog, Model model) {
        String price = dog.getStringPrice();
        if (price == null || !price.matches("[0-9.,]*")) {
            model.addAttribute("errorMessage", "Invalid price. Please enter a valid positive number (0 - 9).");
            model.addAttribute("categories", categoryRepo.findAll());
            model.addAttribute("manufacturers", manufacterRepo.findAll());
            if (dog.getId() == null) {
                return "addClothes";
            } else {
                return "editClothes";
            }
        } else {
            dog.setPrice(Double.parseDouble(price.replace(",", ".")));
            if (dog.getPrice() <= 0) {
                model.addAttribute("errorMessage", "Invalid price. Please enter a valid positive number (0 - 9).");
                model.addAttribute("categories", categoryRepo.findAll());
                model.addAttribute("manufacturers", manufacterRepo.findAll());
                if (dog.getId() == null) {
                    return "addClothes";
                } else {
                    return "editClothes";
                }
            } else {
                dogRepo.save(dog);
                return "redirect:/clothesList";
            }
        }
    }

    @GetMapping("/deleteClothing/{id}")
    public String DeleteClothing(@PathVariable("id") Long dogId) {
        dogRepo.deleteById(dogId);
        return "redirect:/clothesList";
    }

    @GetMapping("/editClothes/{id}")
    public String getMethodName(@PathVariable("id") Long dogId, Model model) {
        Product dog = dogRepo.findById(dogId).get();
        model.addAttribute("dog", dog);
        model.addAttribute("categories", categoryRepo.findAll());
        model.addAttribute("manufacturers", manufacterRepo.findAll());
        return "editClothes";
    }

}
