package k24.tiimi3.dogbackend.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface DogRepository extends CrudRepository<Dog, Long> {
    List<Dog> findByTitle(String title);
}
