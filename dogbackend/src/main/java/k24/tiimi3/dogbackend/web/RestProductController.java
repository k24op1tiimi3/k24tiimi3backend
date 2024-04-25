package k24.tiimi3.dogbackend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import k24.tiimi3.dogbackend.domain.ProductRepository;
<<<<<<< Updated upstream
import k24.tiimi3.dogbackend.domain.Type;
import k24.tiimi3.dogbackend.domain.TypeRepository;
=======
import k24.tiimi3.dogbackend.domain.CategoryRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.GetMapping;
import k24.tiimi3.dogbackend.domain.Product;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api")
public class RestProductController {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private TypeRepository typeRepo;

    @GetMapping("/products")
    public Iterable<Product> GetProducts() {
        return productRepo.findAll();
    }

    @GetMapping("/products/clothing")
    public Iterable<Product> GetClothing() {
        Type type = typeRepo.findByName("Clothing").get(0);
        return productRepo.findByType(type);
    }

}
