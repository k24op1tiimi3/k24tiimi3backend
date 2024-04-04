package k24.tiimi3.dogbackend.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import k24.tiimi3.dogbackend.domain.Category;
import k24.tiimi3.dogbackend.domain.CategoryRepository;

@Controller
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/addcategory")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }

    @PostMapping("/savecategory")
    public String saveCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/categorylist";
    }

    @PostMapping("/deletecategory/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) {
        categoryRepository.deleteById(categoryId);
        return "redirect:/categorylist";
    }    

    @GetMapping("/categorylist")
    public String showCategoryList(Model model) {
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "categorylist";
    }

    @GetMapping("/editcategory/{categoryId}")
    public String showEditCategoryForm(@PathVariable Long categoryId, Model model) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + categoryId));
        model.addAttribute("category", category);
        return "editcategory";
    }

    @PostMapping("/updatecategory")
    public String updateCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/categorylist";
    }
}