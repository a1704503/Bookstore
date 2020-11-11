package palvelinohjelmointi.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
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

	@Autowired
	private BookRepository brep;
	@Autowired
	private CategoryRepository crep;
	@Autowired
	private UserRepository urep;

	@Test
	public void findByTitleReturnBook() {
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
}
