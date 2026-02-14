package app.service.impl;

import app.cache.AppCache;
import app.dto.request.BookCreateRequest;
import app.dto.request.BookUpdateRequest;
import app.dto.response.BookResponse;
import app.mapper.BookMapper;
import app.model.Author;
import app.model.Book;
import app.patterns.builder.BookBuilder;
import app.patterns.factory.BookFactory;
import app.repository.AuthorRepository;
import app.repository.BookRepository;
import app.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private static final String CACHE_ALL_BOOKS = "books:all";

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private final BookFactory bookFactory;

    public BookServiceImpl(BookRepository bookRepo,
                           AuthorRepository authorRepo,
                           BookFactory bookFactory) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.bookFactory = bookFactory;
    }

    private void invalidateBooksCache() {
        AppCache.getInstance().remove(CACHE_ALL_BOOKS);
    }

    @Override
    @Transactional
    public BookResponse create(BookCreateRequest req) {

        Author author = authorRepo.findById(req.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        Book book = new BookBuilder(bookFactory)
                .request(req)
                .author(author)
                .build();

        Book saved = bookRepo.save(book);

        invalidateBooksCache();

        return BookMapper.toResponse(saved);
    }

    @Override
    public List<BookResponse> getAll() {

        AppCache cache = AppCache.getInstance();

        List<BookResponse> cached = cache.get(CACHE_ALL_BOOKS);
        if (cached != null) {
            return cached;
        }

        List<BookResponse> fresh = bookRepo.findAll()
                .stream()
                .map(BookMapper::toResponse)
                .toList();

        cache.put(CACHE_ALL_BOOKS, fresh);

        return fresh;
    }

    @Override
    public BookResponse getById(Long id) {

        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        return BookMapper.toResponse(book);
    }

    @Override
    @Transactional
    public BookResponse update(Long id, BookUpdateRequest req) {

        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (req.getName() != null) {
            book.setName(req.getName());
        }

        if (req.getPrice() != null) {
            book.setPrice(req.getPrice());
        }

        invalidateBooksCache();

        return BookMapper.toResponse(book);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        if (!bookRepo.existsById(id)) {
            throw new RuntimeException("Book not found");
        }

        bookRepo.deleteById(id);

        invalidateBooksCache();
    }

    @Override
    public List<BookResponse> getByAuthor(Long authorId) {

        return bookRepo.findByAuthor_Id(authorId)
                .stream()
                .map(BookMapper::toResponse)
                .toList();
    }

    @Override
    public void clearCacheManually() {
        AppCache.getInstance().clear();
    }
}
