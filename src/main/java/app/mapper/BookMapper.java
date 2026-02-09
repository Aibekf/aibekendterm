package app.mapper;

import app.dto.response.BookResponse;
import app.model.Book;
import app.model.EBook;
import app.model.PrintedBook;

public class BookMapper {

    public static BookResponse toResponse(Book b) {
        String format = null;
        Integer pages = null;

        if (b instanceof EBook eb) {
            format = eb.getFormat();
        } else if (b instanceof PrintedBook pb) {
            pages = pb.getPages();
        }

        return new BookResponse(
                b.getId(),
                b.getType(),
                b.getName(),
                b.getPrice(),
                b.getAuthor().getId(),
                b.getAuthor().getName(),
                format,
                pages
        );
    }
}
