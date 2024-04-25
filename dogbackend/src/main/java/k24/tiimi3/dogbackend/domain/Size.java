package k24.tiimi3.dogbackend.domain;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Size {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long sizeId;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "size")
    @JsonIgnore
    private List<Product> products;


    public Size() {
    }

    public Size(String name) {
        this.name = name;
    }

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Size [id=" + sizeId + ", name=" + name + "]";
    }



}
