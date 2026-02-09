package app.patterns.factory;

import app.exception.InvalidInputException;
import app.model.Author;
import app.model.Book;
import app.model.EBook;
import app.model.PrintedBook;
import org.springframework.stereotype.Component;

@Component
public class BookFactoryImpl implements BookFactory {

    @Override
    public Book create(BookType type, String name, double price, Author author, String format, Integer pages) {
        if (type == null) throw new InvalidInputException("Book type is required");

        return switch (type) {
            case EBOOK -> {
                if (format == null || format.isBlank())
                    throw new InvalidInputException("format is required for EBOOK");
                yield new EBook(name, price, author, format);
            }
            case PRINTED -> {
                if (pages == null || pages <= 0)
                    throw new InvalidInputException("pages must be > 0 for PRINTED book");
                yield new PrintedBook(name, price, author, pages);
            }
        };
    }
}
