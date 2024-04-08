package k24.tiimi3.dogbackend.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {
    List<Manufacturer> findByName(String name);
}
