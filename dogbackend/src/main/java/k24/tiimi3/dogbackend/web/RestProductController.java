package k24.tiimi3.dogbackend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import k24.tiimi3.dogbackend.domain.ProductRepository;
import k24.tiimi3.dogbackend.domain.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import k24.tiimi3.dogbackend.domain.Product;
import k24.tiimi3.dogbackend.domain.Category;

@RestController
@RequestMapping("/api")
public class RestProductController {

    @Autowired
    private ProductRepository dogRepo;
    @Autowired
    private CategoryRepository categoryRepo;

    @GetMapping("/clothes")
    public Iterable<Product> GetClothes() {
        return dogRepo.findAll();
    }

    @GetMapping("/clothes/jackets")
    public Iterable<Product> GetJackets() {
        Category category = categoryRepo.findByName("Jackets").get(0);
        return dogRepo.findByCategory(category);
    }

}
