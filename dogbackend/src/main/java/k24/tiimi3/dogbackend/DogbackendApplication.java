package k24.tiimi3.dogbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k24.tiimi3.dogbackend.domain.Category;
import k24.tiimi3.dogbackend.domain.CategoryRepository;
import k24.tiimi3.dogbackend.domain.Dog;
import k24.tiimi3.dogbackend.domain.DogRepository;

@SpringBootApplication
public class DogbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogbackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CategoryRepository categoryRepository, DogRepository dogRepository) {
		return (args) -> {
			
			Category jackets = new Category("Jackets");
			Category hats = new Category("Hats");
			Category accessories = new Category("Accessories");

			categoryRepository.save(jackets);
			categoryRepository.save(hats);
			categoryRepository.save(accessories);

			dogRepository.save(new Dog("Jacket", "Red", "M", 59.99, "Rukka", jackets));
			dogRepository.save(new Dog("Hat", "Blue", "M", 59.99, "Rukka", hats));
			dogRepository.save(new Dog("Necklace", "Silver", "M", 59.99, "Rukka", accessories));

			log.info("fetch clothes");
			for (Dog dog : dogRepository.findAll()){
				log.info(dog.toString());
			}
		};
	}

}
