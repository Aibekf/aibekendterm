package app.patterns.factory;

import app.model.Author;
import app.model.Book;

public interface BookFactory {
    Book create(BookType type, String name, double price, Author author, String format, Integer pages);
}
