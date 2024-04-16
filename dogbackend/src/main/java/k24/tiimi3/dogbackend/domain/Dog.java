package k24.tiimi3.dogbackend.domain;

import jakarta.persistence.*;

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String color;
    private String size;
    private String stringPrice;
    private double price;


    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "manufacturerId")
    private Manufacturer manufacturer;

    public Dog() {
    }

    public Dog(String title, String color, String size, String stringPrice, double price, Manufacturer manufacturer,
               Category category) {
        this.title = title;
        this.color = color;
        this.size = size;
        this.stringPrice = stringPrice;
        this.price = Double.parseDouble(stringPrice);
        this.manufacturer = manufacturer;
        this.category = category;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    @Override
    public String toString() {

        return "Dog [id=" + id + ", title=" + title + ", color=" + color + ", size=" + size
                + ", price=" + price + ", manufacturer=" + manufacturer + ", category=" + category + "]";
    }

}

