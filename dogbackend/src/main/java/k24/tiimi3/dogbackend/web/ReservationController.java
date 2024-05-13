package k24.tiimi3.dogbackend.web;

import k24.tiimi3.dogbackend.domain.Product;
import k24.tiimi3.dogbackend.domain.ProductRepository;
import k24.tiimi3.dogbackend.domain.Reservation;
import k24.tiimi3.dogbackend.domain.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

@Controller
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private ProductRepository productRepo;

    @PostMapping("/reserveproduct/{productId}")
    public String reserveProduct(@PathVariable Long productId, @RequestParam int quantity,
            RedirectAttributes redirectAttributes) {
        Product product = productRepo.findById(productId).orElse(null);
        if (product == null) {
            redirectAttributes.addFlashAttribute("error", "Product not found");
            return "redirect:/productlist";
        }
        if (product.getQuantity() < quantity) {
            redirectAttributes.addFlashAttribute("error", "Not enough product in stock");
            return "redirect:/productlist";
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepo.save(product);

        Reservation reservation = new Reservation();
        reservation.setProduct(product);
        reservation.setQuantity(quantity);
        reservationRepo.save(reservation);

        redirectAttributes.addFlashAttribute("success", "Product reserved successfully");
        return "redirect:/productlist";
    }

    @GetMapping("/reservations")
    public String getReservations(Model model) {
        model.addAttribute("reservations", reservationRepo.findAll());
        return "reservations";
    }
}