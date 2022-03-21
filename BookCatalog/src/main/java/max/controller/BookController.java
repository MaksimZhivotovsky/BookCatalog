package max.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import max.model.Book;
import max.service.BookService;

@Controller
public class BookController {
	
	private final BookService service;
	
	@Autowired
	public BookController(BookService service) {
		this.service = service;
	}
	
	@RequestMapping("/books")
	public String viewHomePage(Model model) {
		List<Book> listBooks = service.listAll();
		model.addAttribute("listBooks", listBooks);
		
		return "list";
	}
	
	@GetMapping("/create")
    public String createBookForm(Book book){
        return "create";
    }
	
	@PostMapping("/create")
    public String createBook(Book book){
        service.save(book);
        return "redirect:/books";
    }
	
	@GetMapping("delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        service.deleteById(id);
        return "redirect:/books";
    }
	
	@GetMapping("/update/{id}")
    public String updateBookForm(@PathVariable("id") Long id, Model model){
        Book book = service.findeById(id);
        model.addAttribute("book", book);
        return "update";
    }
	
	@PostMapping("/update")
    public String updateBook(Book book){
        service.save(book);
        return "redirect:/books";
    }

}
