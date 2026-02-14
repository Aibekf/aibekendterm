package app.service;

import app.dto.request.BookCreateRequest;
import app.dto.request.BookUpdateRequest;
import app.dto.response.BookResponse;

import java.util.List;

public interface BookService {

    BookResponse create(BookCreateRequest request);

    List<BookResponse> getAll();

    BookResponse getById(Long id);

    BookResponse update(Long id, BookUpdateRequest request);

    void delete(Long id);

    List<BookResponse> getByAuthor(Long authorId);

    void clearCacheManually(); //
}
