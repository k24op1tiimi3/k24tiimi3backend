package k24.tiimi3.dogbackend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k24.tiimi3.dogbackend.domain.Category;
import k24.tiimi3.dogbackend.domain.CategoryRepository;

@SpringBootApplication
public class DogbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DogbackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner kilometritDemo(CategoryRepository CategoryRepository) {
		return (args) -> {

			CategoryRepository.save(new Category("Jackets"));

		};
	}

}
