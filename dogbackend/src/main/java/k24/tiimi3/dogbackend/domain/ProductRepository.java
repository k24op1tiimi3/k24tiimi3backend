package k24.tiimi3.dogbackend.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByTitle(String title);

    List<Product> findByType(Type type);

    List<Product> findByManufacturerNameContainingIgnoreCase(String name);

}
