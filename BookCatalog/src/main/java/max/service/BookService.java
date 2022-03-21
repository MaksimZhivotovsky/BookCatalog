package max.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import max.model.Book;
import max.repository.BookRepository;

@Service
@Transactional
public class BookService {
	
	private final BookRepository repo;
	
	@Autowired
	public BookService(BookRepository repo) {
		this.repo = repo;
	}
	
	public List<Book> listAll() {
		return repo.findAll();
	}
	
	public void save(Book book) {
		repo.save(book);
	}
	
	public Book findeById(Long id) {
		return repo.findById(id).get();
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

}
