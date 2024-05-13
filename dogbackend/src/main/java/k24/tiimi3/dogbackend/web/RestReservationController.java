package k24.tiimi3.dogbackend.web;

import k24.tiimi3.dogbackend.domain.Product;
import k24.tiimi3.dogbackend.domain.ProductRepository;
import k24.tiimi3.dogbackend.domain.Reservation;
import k24.tiimi3.dogbackend.domain.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RestReservationController {

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private ProductRepository productRepo;

    @PostMapping("/reserveproduct/{productId}")
    public ResponseEntity<Map<String, Object>> reserveProduct(@PathVariable Long productId,
            @RequestParam int quantity) {
        Map<String, Object> response = new HashMap<>();
        Product product = productRepo.findById(productId).orElse(null);
        if (product == null) {
            response.put("error", "Product not found");
            return ResponseEntity.badRequest().body(response);
        }
        if (product.getQuantity() < quantity) {
            response.put("error", "Not enough product in stock");
            return ResponseEntity.badRequest().body(response);
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepo.save(product);

        Reservation reservation = new Reservation();
        reservation.setProduct(product);
        reservation.setQuantity(quantity);
        reservationRepo.save(reservation);

        response.put("success", "Product reserved successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/reservations")
    public ResponseEntity<Iterable<Reservation>> getReservations() {
        return ResponseEntity.ok(reservationRepo.findAll());
    }
}
