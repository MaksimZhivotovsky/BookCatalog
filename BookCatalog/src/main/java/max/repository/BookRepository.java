package max.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import max.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
