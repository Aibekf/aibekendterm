package app.service.impl;

import app.dto.request.BookCreateRequest;
import app.dto.request.BookUpdateRequest;
import app.dto.response.BookResponse;
import app.exception.DuplicateResourceException;
import app.exception.InvalidInputException;
import app.exception.ResourceNotFoundException;
import app.mapper.BookMapper;
import app.model.Author;
import app.model.Book;
import app.model.EBook;
import app.model.PrintedBook;
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

    private final BookRepository bookRepo;
    private final AuthorRepository authorRepo;
    private final BookFactory bookFactory;

    public BookServiceImpl(BookRepository bookRepo, AuthorRepository authorRepo, BookFactory bookFactory) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.bookFactory = bookFactory;
    }

    @Override
    @Transactional
    public BookResponse create(BookCreateRequest req) {
        Author author = authorRepo.findById(req.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found: " + req.getAuthorId()));

        if (bookRepo.existsByNameIgnoreCaseAndAuthor_Id(req.getName(), author.getId())) {
            throw new DuplicateResourceException("Book already exists for this author: " + req.getName());
        }

        Book book = new BookBuilder(bookFactory)
                .request(req)
                .author(author)
                .build();

        Book saved = bookRepo.save(book);
        return BookMapper.toResponse(saved);
    }

    @Override
    public List<BookResponse> getAll() {
        return bookRepo.findAll().stream().map(BookMapper::toResponse).toList();
    }

    @Override
    public BookResponse getById(Long id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: " + id));
        return BookMapper.toResponse(book);
    }

    @Override
    @Transactional
    public BookResponse update(Long id, BookUpdateRequest req) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found: " + id));

        if (req.getName() != null && !req.getName().isBlank()) {
            book.setName(req.getName().trim());
        }
        if (req.getPrice() != null) {
            if (req.getPrice() <= 0) throw new InvalidInputException("price must be > 0");
            book.setPrice(req.getPrice());
        }
        if (req.getAuthorId() != null) {
            Author author = authorRepo.findById(req.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found: " + req.getAuthorId()));
            book.setAuthor(author);
        }

        if (book instanceof EBook eb) {
            if (req.getFormat() != null) eb.setFormat(req.getFormat());
        }
        if (book instanceof PrintedBook pb) {
            if (req.getPages() != null) pb.setPages(req.getPages());
        }

        return BookMapper.toResponse(book);
    }

    @Override
    public void delete(Long id) {
        if (!bookRepo.existsById(id)) {
            throw new ResourceNotFoundException("Book not found: " + id);
        }
        bookRepo.deleteById(id);
    }

    @Override
    public List<BookResponse> getByAuthor(Long authorId) {
        return bookRepo.findByAuthor_Id(authorId).stream().map(BookMapper::toResponse).toList();
    }
}
