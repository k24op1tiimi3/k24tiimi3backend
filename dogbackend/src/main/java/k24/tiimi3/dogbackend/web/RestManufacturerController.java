package k24.tiimi3.dogbackend.web;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import k24.tiimi3.dogbackend.domain.ManufacturerRepository;
import k24.tiimi3.dogbackend.domain.Manufacturer;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class RestManufacturerController {

    @Autowired
    private ManufacturerRepository manufacturerRepo;

    @GetMapping("/manufacturers")
    public Iterable<Manufacturer> GetManufacturers() {
        return manufacturerRepo.findAll();
    }
}
