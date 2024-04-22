package k24.tiimi3.dogbackend.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface TypeRepository extends CrudRepository <Type, Long> {
    List<Type> findByName(String name);
}
