package k24.tiimi3.dogbackend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import k24.tiimi3.dogbackend.domain.Type;
import k24.tiimi3.dogbackend.domain.TypeRepository;

@Controller
public class TypeController {
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/typelist")
    public String GetTypes(Model model) {
        model.addAttribute("types", typeRepository.findAll());
        return "typelist";
    }

    @PostMapping("/savetype")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveCategory(@ModelAttribute Type type) {
        typeRepository.save(type);
        return "redirect:/typelist";
    }

    @PostMapping("/deletetype/{typeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteCategory(@PathVariable Long typeId) {
        typeRepository.deleteById(typeId);
        return "redirect:/typelist";
    }

    @GetMapping("/editType/{typeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showEditTypeForm(@PathVariable Long typeId, Model model) {
        Type type = typeRepository.findById(typeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + typeId));
        model.addAttribute("type", type);
        return "editType";
    }

}