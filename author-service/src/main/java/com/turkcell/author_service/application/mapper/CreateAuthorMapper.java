package com.turkcell.author_service.application.mapper;

import com.turkcell.author_service.application.command.CreateAuthorCommand;
import com.turkcell.author_service.application.dto.CreatedAuthorResponse;
import com.turkcell.author_service.domain.model.Author;
import com.turkcell.author_service.domain.model.Name;
import com.turkcell.author_service.domain.model.Surname;
import org.springframework.stereotype.Component;

@Component
public class CreateAuthorMapper {
    public Author toDomain(CreateAuthorCommand command){
        return Author.create(
                new Name(command.name()),
                new Surname(command.surname())
        );
    }
    public CreatedAuthorResponse toResponse(Author author){
        return new CreatedAuthorResponse(
                author.id().value(),
                author.name().value(),
                author.surname().value()
        );
    }
}
