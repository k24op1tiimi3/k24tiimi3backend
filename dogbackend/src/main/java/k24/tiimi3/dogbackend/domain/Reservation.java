package k24.tiimi3.dogbackend.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDate;

    public Reservation() {
    }

    public Reservation(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.reservationDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", product=" + product + ", quantity=" + quantity + ", reservationDate="
                + reservationDate + "]";
    }

}