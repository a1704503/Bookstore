package palvelinohjelmointi.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import palvelinohjelmointi.Bookstore.web.BookController;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookstoreApplicationTests {

	@Autowired
	private BookController bController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(bController).isNotNull();
	}

}
