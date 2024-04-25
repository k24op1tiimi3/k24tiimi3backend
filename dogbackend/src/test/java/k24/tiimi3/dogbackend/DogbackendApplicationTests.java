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
	private ProductRepository productRepository;
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
		productRepository.findAll();
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

		Product product = new Product("Striped Gucci Socks", "Red/Green", "12.90", 12.90, type, manufacturer, size);

		productRepository.save(product);

		// Saving our created dog from Repository.
		Product savedProduct = productRepository.findById(product.getId()).get();

		// Assertions
		// Checking that our dog is saved in dogRepository
		assertNotNull(savedProduct);

		// Checking the components in savedDog match with our new Dog()
		assertEquals("Striped Gucci Socks", savedProduct.getTitle());
		assertEquals("Red/Green", savedProduct.getColor());
		assertEquals("M", savedProduct.getSize().getName());
		assertEquals(12.90, savedProduct.getPrice());
		assertEquals("12.90", savedProduct.getStringPrice());
		assertEquals("Clothing", savedProduct.getType().getName());
		assertEquals("Gucci", savedProduct.getManufacturer().getName());
	}
}
