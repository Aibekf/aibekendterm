package app.service;

import app.dto.request.AuthorCreateRequest;
import app.dto.response.AuthorResponse;

import java.util.List;

public interface AuthorService {
    AuthorResponse create(AuthorCreateRequest req);
    List<AuthorResponse> getAll();
    AuthorResponse getById(Long id);

    AuthorResponse update(Long id, AuthorCreateRequest req);
    void delete(Long id);
}

