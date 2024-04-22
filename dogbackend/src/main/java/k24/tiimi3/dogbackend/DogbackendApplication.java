package k24.tiimi3.dogbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k24.tiimi3.dogbackend.domain.Manufacturer;
import k24.tiimi3.dogbackend.domain.ManufacturerRepository;
import k24.tiimi3.dogbackend.domain.Product;
import k24.tiimi3.dogbackend.domain.ProductRepository;
import k24.tiimi3.dogbackend.domain.Size;
import k24.tiimi3.dogbackend.domain.SizeRepository;
import k24.tiimi3.dogbackend.domain.Type;
import k24.tiimi3.dogbackend.domain.TypeRepository;

@SpringBootApplication
public class DogbackendApplication {

    private static final Logger log = LoggerFactory.getLogger(DogbackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DogbackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(ProductRepository productRepository, ManufacturerRepository manufacturerRepository, 
    SizeRepository sizeRepository, TypeRepository typeRepository) {
        return (args) -> {

            Manufacturer Rukka = new Manufacturer("Rukka");
            Manufacturer Nukka = new Manufacturer("Nukka");
            Manufacturer Wilson = new Manufacturer("Wilson Sporting Goods");

            Size sizeS = new Size("S");
            Size sizeM = new Size("M");
            Size sizeL = new Size("L");

            Type clothing = new Type("Clothing");
            Type toy = new Type("Toy");


            manufacturerRepository.save(Rukka);
            manufacturerRepository.save(Nukka);
            manufacturerRepository.save(Wilson);

            sizeRepository.save(sizeS);
            sizeRepository.save(sizeM);
            sizeRepository.save(sizeL);

            typeRepository.save(clothing);
            typeRepository.save(toy);

            productRepository.save(new Product("Jacket", "Red", "59.99", 59.99, clothing, Rukka, sizeL));
            productRepository.save(new Product("Hat", "Blue", "25.99", 19.99, clothing, Nukka, sizeM));
            productRepository.save(new Product("Tennis Balls", "Yellow", "19.99", 29.99, toy, Wilson, sizeS));

            // Check added categories, manufacturers and clothes
            log.info("------------------------------");

            log.info("Fetch Categories");

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
