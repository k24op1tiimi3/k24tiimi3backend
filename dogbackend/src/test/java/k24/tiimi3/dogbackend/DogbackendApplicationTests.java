package k24.tiimi3.dogbackend;

import k24.tiimi3.dogbackend.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    @Autowired
    private AppUserRepository userRepository;


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
        userRepository.findAll();
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

    // Creating a new user and saving the user to our AppUserRepository
    @Test
    void addingNewUserToRepository() {

        AppUser user = new AppUser("testUser", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");

        userRepository.save(user);

        // Saving our created user from Repository.
        AppUser savedUser = userRepository.findById(user.getId()).get();

        // Assertions
        // Checking that our user is saved in userRepository
        assertNotNull(savedUser);

        // Checking the components in savedUser match with our new User()
        assertEquals("testUser", savedUser.getUsername());
        assertEquals("$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", savedUser.getPassword());
        assertEquals("USER", savedUser.getRole());
    }
}

