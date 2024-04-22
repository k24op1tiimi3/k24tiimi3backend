package k24.tiimi3.dogbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k24.tiimi3.dogbackend.domain.Manufacturer;
import k24.tiimi3.dogbackend.domain.ManufacturerRepository;
import k24.tiimi3.dogbackend.domain.Category;
import k24.tiimi3.dogbackend.domain.CategoryRepository;
import k24.tiimi3.dogbackend.domain.Product;
import k24.tiimi3.dogbackend.domain.ProductRepository;
import k24.tiimi3.dogbackend.domain.Size;
import k24.tiimi3.dogbackend.domain.SizeRepository;

@SpringBootApplication
public class DogbackendApplication {

    private static final Logger log = LoggerFactory.getLogger(DogbackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DogbackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(CategoryRepository categoryRepository, ProductRepository productRepository,
            ManufacturerRepository manufacturerRepository, SizeRepository sizeRepository) {
        return (args) -> {

            Category jackets = new Category("Jackets");
            Category hats = new Category("Hats");
            Category accessories = new Category("Accessories");

            Manufacturer Rukka = new Manufacturer("Rukka");
            Manufacturer Nukka = new Manufacturer("Nukka");
            Manufacturer Zukka = new Manufacturer("Zukka");

            Size sizeS = new Size("S");
            Size sizeM = new Size("M");
            Size sizeL = new Size("L");


            categoryRepository.save(jackets);
            categoryRepository.save(hats);
            categoryRepository.save(accessories);

            manufacturerRepository.save(Rukka);
            manufacturerRepository.save(Nukka);
            manufacturerRepository.save(Zukka);

            sizeRepository.save(sizeS);
            sizeRepository.save(sizeM);
            sizeRepository.save(sizeL);

            productRepository.save(new Product("Jacket", "Red", "59.99", 59.99, jackets, Rukka, sizeL));
            productRepository.save(new Product("Hat", "Blue", "19.99", 19.99, hats, Nukka, sizeM));
            productRepository.save(new Product("Necklace", "Silver", "29.99", 29.99, accessories, Zukka, sizeS));

            // Check added categories, manufacturers and clothes
            log.info("------------------------------");

            log.info("Fetch Categories");
            for (Category category : categoryRepository.findAll()) {
                log.info(category.toString());
            }
            log.info("------------------------------");

            log.info("Fetch Manufacturers");
            for (Manufacturer manufacturers : manufacturerRepository.findAll()) {
                log.info(manufacturers.toString());
            }
            log.info("------------------------------");

            log.info("Fetch Clothes");
            for (Product product : productRepository.findAll()) {
                log.info(product.toString());
            }
            log.info("------------------------------");
        };
    }

}
