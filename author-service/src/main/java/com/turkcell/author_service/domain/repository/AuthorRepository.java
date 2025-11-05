package com.turkcell.author_service.domain.repository;

import com.turkcell.author_service.domain.model.Author;
import com.turkcell.author_service.domain.model.AuthorId;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);
    Optional<Author> findById(AuthorId authorId);
    List<Author> findAll();
    List<Author> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(AuthorId authorId);
}
