package k24.tiimi3.dogbackend.web;

import k24.tiimi3.dogbackend.domain.Manufacturer;
import k24.tiimi3.dogbackend.domain.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManufacturerController {

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @GetMapping("/manufacturerList")
    public String getManufacturers(Model model) {
        model.addAttribute("manufacturers", manufacturerRepository.findAll());
        model.addAttribute("manufacturer", new Manufacturer());
        return "manufacturerList";
    }

    @GetMapping("/addmanufacturer")
    public String showAddManufacturerForm(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        return "addmanufacturer";
    }

    @PostMapping("/savemanufacturer")
    public String saveManufacturer(@ModelAttribute Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
        return "redirect:/manufacturerList";
    }

    @PostMapping("/deletemanufacturer/{manufacturerId}")
    public String deleteManufacturer(@PathVariable Long manufacturerId) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid manufacturer Id:" + manufacturerId));

        if (manufacturer.getDogs().isEmpty()) {
            manufacturerRepository.delete(manufacturer);
        } else {
            // System output for the error message:
            System.out.println("Cannot delete manufacturer. There are products associated with it.");
        }
        return "redirect:/manufacturerList";
    }

    @GetMapping("/editmanufacturer/{manufacturerId}")
    public String showEditManufacturerForm(@PathVariable Long manufacturerId, Model model) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid manufacturer Id:" + manufacturerId));
        model.addAttribute("manufacturer", manufacturer);
        return "editmanufacturer";
    }

    @PostMapping("/updatemanufacturer")
    public String updateManufacturer(@ModelAttribute Manufacturer manufacturer) {
        manufacturerRepository.save(manufacturer);
        return "redirect:/manufacturerList";
    }
}
