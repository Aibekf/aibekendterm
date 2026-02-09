package app.mapper;

import app.dto.response.AuthorResponse;
import app.model.Author;

public class AuthorMapper {
    public static AuthorResponse toResponse(Author a) {
        return new AuthorResponse(a.getId(), a.getName());
    }
}
