package k24.tiimi3.dogbackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import k24.tiimi3.dogbackend.domain.Product;
import k24.tiimi3.dogbackend.domain.ProductRepository;
import k24.tiimi3.dogbackend.domain.Size;
import k24.tiimi3.dogbackend.domain.SizeRepository;
import k24.tiimi3.dogbackend.domain.Type;
import k24.tiimi3.dogbackend.domain.TypeRepository;
import k24.tiimi3.dogbackend.domain.Manufacturer;
import k24.tiimi3.dogbackend.domain.ManufacturerRepository;

@SpringBootTest
class DogbackendApplicationTests {

	@Autowired
	private ProductRepository dogRepository;
	@Autowired
	private TypeRepository typeRepository;
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	@Autowired
	private SizeRepository sizeRepository;

	@Test
	void contextLoads() {
	}

	// Trying to find data saved inside the Repository
	@Test
	void dataFoundInRepository() {
		dogRepository.findAll();
		typeRepository.findAll();
		manufacturerRepository.findAll();
		sizeRepository.findAll();
	}

	// Creating a new entity and saving the entity to our DogRepository
	@Test
	void insertingNewEntityToRepository() {

		Type type = new Type("Clothing");
		typeRepository.save(type);

		Manufacturer manufacturer = new Manufacturer("Gucci");
		manufacturerRepository.save(manufacturer);

		Size size = new Size("M");
		sizeRepository.save(size);

		Product dog = new Product("Striped Gucci Socks", "Red/Green", "12.90", 12.90, type, manufacturer, size);

		dogRepository.save(dog);

		// Saving our created dog from Repository.
		Product savedDog = dogRepository.findById(dog.getId()).get();

		// Assertions
		// Checking that our dog is saved in dogRepository
		assertNotNull(savedDog);

		// Checking the components in savedDog match with our new Dog()
		assertEquals("Striped Gucci Socks", savedDog.getTitle());
		assertEquals("Red/Green", savedDog.getColor());
		assertEquals("M", savedDog.getSize().getSize());
		assertEquals(12.90, savedDog.getPrice());
		assertEquals("12.90", savedDog.getStringPrice());
		assertEquals("Clothing", savedDog.getType().getName());
		assertEquals("Gucci", savedDog.getManufacturer().getName());
	}
}
