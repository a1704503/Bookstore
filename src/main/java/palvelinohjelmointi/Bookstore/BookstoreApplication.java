package palvelinohjelmointi.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import palvelinohjelmointi.Bookstore.domain.Book;
import palvelinohjelmointi.Bookstore.domain.BookRepository;
import palvelinohjelmointi.Bookstore.domain.Category;
import palvelinohjelmointi.Bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository cRepository) {
		return (args) -> {

			log.info("save few categories");
			cRepository.save(new Category("Tietokirjallisuus"));
			cRepository.save(new Category("Romaanit"));
			cRepository.save(new Category("Kauhu"));

			log.info("save a couple of books");

			// Two books

			bookRepository.save(
					new Book("Äyskäri", "Äyskäriini", 1858, "0001", "30€", cRepository.findByName("Romaanit").get(0)));
			bookRepository.save(new Book("21 Oppituntia maailman tilasta", "Yuval Noah Harrari", 2018,
					"978-952-279-609-7", "50€", cRepository.findByName("Tietokirjallisuus").get(0)));

			log.info("fetch all categories");
			for (Category c : cRepository.findAll()) {
				log.info(c.toString());
			}

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
