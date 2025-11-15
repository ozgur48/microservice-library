package com.turkcell.author_service.application.mapper;

import com.turkcell.author_service.application.dto.AuthorDetails;
import com.turkcell.author_service.domain.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorDetailsMapper {
    public AuthorDetails toResponse(Author author){
        return new AuthorDetails(
                author.name().value(),
                author.surname().value()
        );
    }
}
