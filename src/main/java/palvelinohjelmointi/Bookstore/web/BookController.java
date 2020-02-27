package palvelinohjelmointi.Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

	// Empty object for user
	@GetMapping(value = "/newbook")
	public String newBookForm(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", cRepository.findAll());
		return "addbook";
	}

	// Save user input & send to db
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

	// Delete book (id)
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
