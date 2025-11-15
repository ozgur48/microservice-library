package com.turkcell.author_service.application.query;

import com.turkcell.author_service.application.dto.AuthorDetails;
import com.turkcell.author_service.application.mapper.AuthorDetailsMapper;
import com.turkcell.author_service.core.cqrs.QueryHandler;
import com.turkcell.author_service.domain.exception.AuthorNotFoundException;
import com.turkcell.author_service.domain.model.Author;
import com.turkcell.author_service.domain.model.AuthorId;
import com.turkcell.author_service.domain.repository.AuthorRepository;
import org.springframework.stereotype.Component;

@Component
public class GetAuthorDetailsQueryHandler implements QueryHandler<GetAuthorDetailsQuery, AuthorDetails> {
    private final AuthorRepository authorRepository;
    private final AuthorDetailsMapper authorDetailsMapper;

    public GetAuthorDetailsQueryHandler(AuthorRepository authorRepository, AuthorDetailsMapper authorDetailsMapper) {
        this.authorRepository = authorRepository;
        this.authorDetailsMapper = authorDetailsMapper;
    }

    @Override
    public AuthorDetails handle(GetAuthorDetailsQuery query) {
        Author author = authorRepository.findById(new AuthorId(query.id()))
                .orElseThrow(()-> new AuthorNotFoundException(query.id()));
        return authorDetailsMapper.toResponse(author);
    }
}
