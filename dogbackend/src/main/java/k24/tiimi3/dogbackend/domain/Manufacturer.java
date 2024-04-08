
package k24.tiimi3.dogbackend.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manufacturerId;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "manufacturer")
    private List<Dog> dogs;

    public Manufacturer() {
    }

    public Manufacturer(String name) {
        this.name = name;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setCategoryId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "[manufacturerId=" + manufacturerId + ",name=" + name + "]";
    }
}
