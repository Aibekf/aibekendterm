package app.controller;

import app.dto.request.BookCreateRequest;
import app.dto.request.BookUpdateRequest;
import app.dto.response.BookResponse;
import app.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookResponse create(@Valid @RequestBody BookCreateRequest req) {
        return bookService.create(req);
    }

    @GetMapping
    public List<BookResponse> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookResponse getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id, @Valid @RequestBody BookUpdateRequest req) {
        return bookService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        bookService.delete(id);
        return "Deleted book id=" + id;
    }

    @GetMapping("/by-author/{authorId}")
    public List<BookResponse> byAuthor(@PathVariable Long authorId) {
        return bookService.getByAuthor(authorId);
    }
}
