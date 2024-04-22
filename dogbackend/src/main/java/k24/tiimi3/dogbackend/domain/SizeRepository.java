package k24.tiimi3.dogbackend.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface SizeRepository extends CrudRepository <Size, Long>{
    List<Size> findBySize(String size);
}
