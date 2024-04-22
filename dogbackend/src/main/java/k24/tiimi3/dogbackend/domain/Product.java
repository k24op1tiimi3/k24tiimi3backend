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

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "manufacturerId")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Size size;


    
    public Product() {
    }

    public Product(String title, String color, String stringPrice, double price, Category category,
            Manufacturer manufacturer, Size size) {
        this.title = title;
        this.color = color;
        this.stringPrice = stringPrice;
        this.price = price;
        this.category = category;
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

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    
    @Override
    public String toString() {

        return "Dog [id=" + id + ", title=" + title + ", color=" + color + ", size=" + size
                + ", price=" + price + ", manufacturer=" + manufacturer + ", category=" + category + "]";
    }
}
