package palvelinohjelmointi.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import palvelinohjelmointi.Bookstore.domain.Book;
import palvelinohjelmointi.Bookstore.domain.BookRepository;
import palvelinohjelmointi.Bookstore.domain.Category;
import palvelinohjelmointi.Bookstore.domain.CategoryRepository;
import palvelinohjelmointi.Bookstore.domain.User;
import palvelinohjelmointi.Bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookstoreRepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	@Autowired
	private BookRepository brep;
	@Autowired
	private CategoryRepository crep;
	@Autowired
	private UserRepository urep;

	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = brep.findByTitle("Panssari");
		assertThat(books).hasSize(1);
		boolean bContentEqls = books.get(0).getTitle().contentEquals("Panssari");
		assertThat(bContentEqls);
	}

	@Test
	public void findByNameShouldReturnCategory() {
		List<Category> categories = crep.findByName("Tietokirjallisuus");
		assertThat(categories).hasSize(1);
		boolean cContentEqls = categories.get(0).getName().contentEquals("Romaanit");
		assertThat(cContentEqls);
	}

	@Test
	public void findByUsernameShouldReturnUser() {
		User user = urep.findByUsername("user");
		assertThat(user).isNotNull();
		boolean uContentEqls = user.getUsername().contentEquals("user");
		assertThat(uContentEqls);
	}

	@Test
	public void createNewBook() {
		Book book = new Book("Raineri", "Perakainen", 1945, "01", "12€", crep.findByName("Romaanit").get(0));
		brep.save(book);
		assertThat(book.getId()).isNotNull();
	}

	@Test
	public void createNewCategory() {
		Category category = new Category("Erotiikka");
		crep.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}

	@Test
	public void createNewUser() {
		User user = new User("test", "$2y$06$V5eG.lzxyMq60QbNgaBh5.KPA1mzpHGJhs8oQaFbaj9FqzHp5gprO", "test@gmail.com",
				"USER");
		urep.save(user);
		assertThat(user.getId()).isNotNull();
	}

	@Test
	public void deleteBook() {
		List<Book> books = brep.findByTitle("Panssari");
		Book book = books.get(0);
		assertThat(books.size() == 1); // Before delete

		brep.deleteById(book.getId());
		books = brep.findByTitle(book.getTitle()); // book.getTittle() = "Panssari"
		assertThat(books.size() == 0); // After delete
	}

	@Test
	public void deleteCategory() {
		List<Category> categories = crep.findByName("Tietokirjallisuus");
		Category category = categories.get(0);
		assertThat(categories.size() == 1);

		crep.deleteById(category.getCategoryid());
		categories = crep.findByName(category.getName());
		assertThat(categories.size() == 0);
	}

	@Test
	public void deleteUser() {
		User user = urep.findByUsername("user");
		assertThat(user.getUsername()).isNotNull();
		assertThat(user.getUsername()).isEqualTo("user");

		urep.delete(user);
		user = urep.findByUsername("user");
		assertThat(user).isNull();
	}
}
