package max.rest;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import max.model.Book;
import max.service.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookRestControllerV1 {
	
	private final BookService service;
	
	@Autowired
	public BookRestControllerV1(BookService service) {
		this.service = service;
		
	}

	@GetMapping
	public List<Book> getAll() {
		return service.listAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> get(@PathVariable Long id) {
		try {
			Book book = service.findeById(id);
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('developers:read')")
	public void save(@RequestBody Book book) {
		service.save(book);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public ResponseEntity<?> update(@RequestBody Book book, @PathVariable Long id) {
		try {
			Book existbook = service.findeById(id);
			service.save(book);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('developers:write')")
	public void delete(@PathVariable Long id) {
	    service.deleteById(id);
	}
	
}
