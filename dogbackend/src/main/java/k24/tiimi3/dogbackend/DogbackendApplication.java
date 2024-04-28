package k24.tiimi3.dogbackend;

import k24.tiimi3.dogbackend.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DogbackendApplication {

    private static final Logger log = LoggerFactory.getLogger(DogbackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DogbackendApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner demo(ProductRepository productRepository, ManufacturerRepository manufacturerRepository, SizeRepository sizeRepository, TypeRepository typeRepository, AppUserRepository urepository) {
        return (args) -> {

            Manufacturer Rukka = new Manufacturer("Rukka");
            Manufacturer Nukka = new Manufacturer("Nukka");
            Manufacturer Wilson = new Manufacturer("Wilson Sporting Goods");

            Size sizeS = new Size("S");
            Size sizeM = new Size("M");
            Size sizeL = new Size("L");

            Type clothing = new Type("Clothing");
            Type toy = new Type("Toy");


            // salasana = (username)
            AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
            AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
            urepository.save(user1);
            urepository.save(user2);

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


            log.info("-------------------");
            log.info("Fetch all products:");
            for (Product product : productRepository.findAll()) {
                log.info(product.toString());
            }
            log.info("-------------------");

            log.info("Fetch all manufacturers:");
            for (Manufacturer manufacturer : manufacturerRepository.findAll()) {
                log.info(manufacturer.toString());
            }
            log.info("-------------------");

            log.info("Fetch all sizes:");
            for (Size size : sizeRepository.findAll()) {
                log.info(size.toString());
            }
            log.info("-------------------");

            log.info("Fetch all types:");
            for (Type type : typeRepository.findAll()) {
                log.info(type.toString());
            }
            log.info("-------------------");

            log.info("Fetch all users:");
            for (AppUser user : urepository.findAll()) {
                log.info(user.toString());
            }
            log.info("-------------------");
        };
    }
}