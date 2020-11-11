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
import palvelinohjelmointi.Bookstore.domain.User;
import palvelinohjelmointi.Bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, CategoryRepository cRepository,
			UserRepository uRepository) {
		return (args) -> {

			// Add test categories here
			log.info("save few categories");
			cRepository.save(new Category("Tietokirjallisuus"));
			cRepository.save(new Category("Romaanit"));
			cRepository.save(new Category("Kauhu"));

			log.info("save a couple of books");
			// Tree books
			bookRepository.save(
					new Book("Panssari", "Perakainen", 1955, "08", "10€", cRepository.findByName("Romaanit").get(0)));
			bookRepository.save(
					new Book("Äyskäri", "Äyskäriini", 1858, "0001", "30€", cRepository.findByName("Romaanit").get(0)));
			bookRepository.save(new Book("21 Oppituntia maailman tilasta", "Yuval Noah Harrari", 2018,
					"978-952-279-609-7", "50€", cRepository.findByName("Tietokirjallisuus").get(0)));

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"user@gmail.com", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"admin@hotmail.com", "ADMIN");
			uRepository.save(user1);
			uRepository.save(user2);

			log.info("fetch all categories");
			for (Category c : cRepository.findAll()) {
				log.info(c.toString());
			}

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

			log.info("fetch all users");
			for (User user : uRepository.findAll()) {
				log.info("Role: " + user.getRole());
			}
		};
	}
}
