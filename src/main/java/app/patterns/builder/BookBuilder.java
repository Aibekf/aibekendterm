package app.patterns.builder;

import app.dto.request.BookCreateRequest;
import app.model.Author;
import app.model.Book;
import app.patterns.factory.BookFactory;

public class BookBuilder {
    private final BookFactory factory;
    private BookCreateRequest req;
    private Author author;

    public BookBuilder(BookFactory factory) {
        this.factory = factory;
    }

    public BookBuilder request(BookCreateRequest req) {
        this.req = req;
        return this;
    }

    public BookBuilder author(Author author) {
        this.author = author;
        return this;
    }

    public Book build() {
        return factory.create(
                req.getType(),
                req.getName(),
                req.getPrice(),
                author,
                req.getFormat(),
                req.getPages()
        );
    }
}
