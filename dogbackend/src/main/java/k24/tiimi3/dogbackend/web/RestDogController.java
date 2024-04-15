package k24.tiimi3.dogbackend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import k24.tiimi3.dogbackend.domain.DogRepository;
import k24.tiimi3.dogbackend.domain.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import k24.tiimi3.dogbackend.domain.Dog;
import k24.tiimi3.dogbackend.domain.Category;

@RestController
@RequestMapping("/api")
public class RestDogController {

    @Autowired
    private DogRepository dogRepo;
    @Autowired
    private CategoryRepository categoryRepo;

   @GetMapping("/clothes")
    public Iterable<Dog> GetClothes() {
        return dogRepo.findAll();
    }

    @GetMapping("/clothes/jackets")
    public Iterable<Dog> GetJackets() {
        Category category = categoryRepo.findByName("Jackets").get(0);
        return dogRepo.findByCategory(category);}

}
