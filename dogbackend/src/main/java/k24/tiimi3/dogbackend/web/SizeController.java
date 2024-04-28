package k24.tiimi3.dogbackend.web;

import k24.tiimi3.dogbackend.domain.Size;
import k24.tiimi3.dogbackend.domain.SizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SizeController {
    @Autowired
    private SizeRepository sizeRepository;

    @GetMapping("/sizelist")
    public String getSizes(Model model) {
        model.addAttribute("sizes", sizeRepository.findAll());
        return "sizelist";
    }

    @PostMapping("/savesize")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveSize(@ModelAttribute Size size) {
        sizeRepository.save(size);
        System.out.println(sizeRepository.toString());
        return "redirect:/sizelist";
    }

    @PostMapping("/deletesize/{sizeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteSize(@PathVariable Long sizeId) {
        Size size = sizeRepository.findById(sizeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid size Id:" + sizeId));

        if (size.getProducts().isEmpty()) {
            sizeRepository.delete(size);
        } else {
            // System output for the error message:
            System.out.println("Cannot delete size. There are products associated with it.");
        }
        return "redirect:/sizelist";
    }

    @GetMapping("/editsize/{sizeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String showEditSizeForm(@PathVariable Long sizeId, Model model) {
        Size size = sizeRepository.findById(sizeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid size Id:" + sizeId));
        model.addAttribute("size", size);
        return "editsize";
    }
}
