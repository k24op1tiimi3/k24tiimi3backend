package k24.tiimi3.dogbackend.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String type;
    private String color;
    private String size;
    private double price;
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    public Dog(String title, String author, int publicationYear, String isbn, double price,
            Category category) {
        this.title = title;
        this.type = type;
        this.color = color;
        this.size = size;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public Dog() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        if (this.category != null) {
            return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear
                    + ", isbn=" + isbn
                    + ", price=" + price + ", category=" + this.getCategory() + "]";
        } else {
            return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear
                    + ", isbn=" + isbn
                    + ", price=" + price + "]";
        }
    }

}
