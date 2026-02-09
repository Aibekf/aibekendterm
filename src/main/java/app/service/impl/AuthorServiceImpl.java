package app.service.impl;

import app.dto.request.AuthorCreateRequest;
import app.dto.response.AuthorResponse;
import app.exception.DuplicateResourceException;
import app.exception.ResourceNotFoundException;
import app.mapper.AuthorMapper;
import app.model.Author;
import app.repository.AuthorRepository;
import app.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepo;

    public AuthorServiceImpl(AuthorRepository authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public AuthorResponse create(AuthorCreateRequest req) {
        String name = req.getName().trim();
        if (authorRepo.existsByNameIgnoreCase(name)) {
            throw new DuplicateResourceException("Author already exists: " + name);
        }
        Author saved = authorRepo.save(new Author(name));
        return AuthorMapper.toResponse(saved);
    }

    @Override
    public List<AuthorResponse> getAll() {
        return authorRepo.findAll().stream().map(AuthorMapper::toResponse).toList();
    }

    @Override
    public AuthorResponse getById(Long id) {
        Author a = authorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found: " + id));
        return AuthorMapper.toResponse(a);
    }
}
