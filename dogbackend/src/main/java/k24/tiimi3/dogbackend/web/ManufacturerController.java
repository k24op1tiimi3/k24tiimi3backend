
package k24.tiimi3.dogbackend.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import k24.tiimi3.dogbackend.domain.Manufacturer;
import k24.tiimi3.dogbackend.domain.ManufacturerRepository;


@Controller

public class ManufacturerController {
    @Autowired
    private ManufacturerRepository manufacturerRepository;



    // Shows all manufacturers
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
        return "redirect:/manufacturerlist";
    }

    @PostMapping("/deletemanufacturer/{manufacturerId}")
    public String deleteManufacturer(@PathVariable Long manufacturerId) {
        manufacturerRepository.deleteById(manufacturerId);
        return "redirect:/manufacturerlist";
    }    

    @GetMapping("/editmanufacturers/{manufacturerId}")
    public String showEditManufacturerForm(@PathVariable Long manufacturerId, Model model) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + manufacturerId));
        model.addAttribute("manufacturer", manufacturer);
        return "editmanufacturer";
    }
}