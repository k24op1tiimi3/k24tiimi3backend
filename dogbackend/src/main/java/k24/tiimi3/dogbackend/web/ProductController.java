package k24.tiimi3.dogbackend.web;

import k24.tiimi3.dogbackend.domain.Product;
import k24.tiimi3.dogbackend.domain.ProductRepository;
import k24.tiimi3.dogbackend.domain.SizeRepository;
import k24.tiimi3.dogbackend.domain.TypeRepository;
import k24.tiimi3.dogbackend.domain.ManufacturerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private TypeRepository typeRepo;
    @Autowired
    private ManufacturerRepository manufacterRepo;
    @Autowired
    private SizeRepository sizeRepo;

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String GetIndex(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "index";
    }

    @GetMapping("/productlist")
    public String getClothes(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "productlist";
    }

    @GetMapping("/addproduct")
    public String AddNewClothing(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("types", typeRepo.findAll());
        model.addAttribute("sizes", sizeRepo.findAll());
        model.addAttribute("manufacturers", manufacterRepo.findAll());
        return "addproduct";
    }

    @PostMapping("/saveproduct")
    public String SaveClothing(@ModelAttribute Product product, Model model) {
        String price = product.getStringPrice();
        if (price == null || !price.matches("[0-9.,]*")) {
            model.addAttribute("errorMessage", "Invalid price. Please enter a valid positive number (0 - 9).");
            model.addAttribute("types", typeRepo.findAll());
            model.addAttribute("manufacturers", manufacterRepo.findAll());
            if (product.getId() == null) {
                return "addproduct";
            } else {
                return "editproduct";
            }
        } else {
            product.setPrice(Double.parseDouble(price.replace(",", ".")));
            if (product.getPrice() <= 0) {
                model.addAttribute("errorMessage", "Invalid price. Please enter a valid positive number (0 - 9).");
                model.addAttribute("types", typeRepo.findAll());
                model.addAttribute("manufacturers", manufacterRepo.findAll());
                if (product.getId() == null) {
                    return "addproduct";
                } else {
                    return "editproduct";
                }
            } else {
                productRepo.save(product);
                return "redirect:/productlist";
            }
        }
    }

    @GetMapping("/deleteproduct/{id}")
    public String DeleteClothing(@PathVariable("id") Long productId) {
        productRepo.deleteById(productId);
        return "redirect:/productlist";
    }

    @GetMapping("/editproduct/{id}")
    public String getMethodName(@PathVariable("id") Long productId, Model model) {
        Product product = productRepo.findById(productId).get();
        model.addAttribute("product", product);
        model.addAttribute("types", typeRepo.findAll());
        model.addAttribute("sizes", sizeRepo.findAll());
        model.addAttribute("manufacturers", manufacterRepo.findAll());
        return "editproduct";
    }

}
