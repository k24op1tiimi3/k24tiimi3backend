package k24.tiimi3.dogbackend.domain;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String color;
    private String stringPrice;
    private double price;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "manufacturerId")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Size size;

    public Product() {
    }

    public Product(String title, String color, String stringPrice, double price, int quantity, Type type,
            Manufacturer manufacturer,
            Size size) {
        this.title = title;
        this.color = color;
        this.stringPrice = stringPrice;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
        this.manufacturer = manufacturer;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStringPrice() {
        return stringPrice;
    }

    public void setStringPrice(String stringPrice) {
        this.stringPrice = stringPrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", title=" + title + ", color=" + color + ", stringPrice=" + stringPrice
                + ", price=" + price + ", type=" + type + ", manufacturer=" + manufacturer + ", size=" + size + "]";
    }
}
