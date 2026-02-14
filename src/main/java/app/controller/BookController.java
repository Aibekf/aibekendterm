package app.controller;

import app.dto.request.BookCreateRequest;
import app.dto.request.BookUpdateRequest;
import app.dto.response.BookResponse;
import app.service.BookService;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse create(@RequestBody BookCreateRequest request) {
        return bookService.create(request);
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
    public BookResponse update(@PathVariable Long id,
                               @RequestBody BookUpdateRequest request) {
        return bookService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @GetMapping("/author/{authorId}")
    public List<BookResponse> getByAuthor(@PathVariable Long authorId) {
        return bookService.getByAuthor(authorId);
    }

    @DeleteMapping("/cache")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCache() {
        bookService.clearCacheManually();
    }
}

