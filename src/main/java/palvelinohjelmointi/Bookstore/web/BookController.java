package palvelinohjelmointi.Bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import palvelinohjelmointi.Bookstore.domain.Book;
import palvelinohjelmointi.Bookstore.domain.BookRepository;
import palvelinohjelmointi.Bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	BookRepository repository;

	@Autowired
	CategoryRepository cRepository;

	// Show booklist
	@GetMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	// Show booklist JSON
	@GetMapping("/books")
	public @ResponseBody List<Book> bookListRest() {
		// Showing a list instead just one object
		return (List<Book>) repository.findAll();
	}

	// Empty object for user
	@GetMapping(value = "/newbook")
	public String newBookForm(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", cRepository.findAll());
		return "addbook";
	}

	// Save user input & send to db
	// Return user to listing view
	@PostMapping(value = "/newbook")
	public String saveNewBook(@ModelAttribute Book book) {
		repository.save(book);
		return "redirect:/booklist";
	}

	// Book object for user edit (id)
	@GetMapping(value = "/editbook/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("categories", cRepository.findAll());
		return "editbook";
	}

	// Find by ID Rest
	@GetMapping(value = "/book/{id}")
	public @ResponseBody Optional<Book> findByIdRest(@PathVariable("id") Long bookId) {
		// return "optional" type booklist -object, requires this to handle exceptions
		return (Optional<Book>) repository.findById(bookId);
	}

	// Delete book (id)
	// Authorised only for admin
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/deletebook/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}

	/*
	 * @GetMapping("/index") public String indexPage() { return "index"; }
	 */
	/*
	 * @PostMapping(value = "/editbook/{id}") public String
	 * editBook(@PathVariable("id") Long bookId, Book book, Model model) {
	 * model.addAttribute("editbook", repository.findById(bookId));
	 * repository.save(book); return "redirect:/booklist"; }
	 * 
	 * // Save user edit input & send to db
	 * 
	 * @PostMapping(value = "/editbook") public String saveEditBook(@ModelAttribute
	 * Book book) { repository.save(book); return "redirect:/booklist"; }
	 */
}
