package palvelinohjelmointi.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import palvelinohjelmointi.Bookstore.domain.Book;
import palvelinohjelmointi.Bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository) {
		return (args) -> {
			log.info("save a couple of books");
			bookRepository.save(new Book("Reiska", null, 1996, null, null));
			bookRepository.save(new Book("Äyskäri", null, 1858, null, null));
			bookRepository.save(
					new Book("21 Oppituntia maailman tilasta", "Yuval Noah Harrari", 2018, "978-952-279-609-7", "50€"));

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
