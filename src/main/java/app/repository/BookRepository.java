package app.repository;

import app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor_Id(Long authorId);
    boolean existsByNameIgnoreCaseAndAuthor_Id(String name, Long authorId);
}
