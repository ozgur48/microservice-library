package com.turkcell.author_service.application.mapper;

import com.turkcell.author_service.application.command.UpdateAuthorCommand;
import com.turkcell.author_service.application.dto.UpdatedAuthorResponse;
import com.turkcell.author_service.domain.model.Author;
import com.turkcell.author_service.domain.model.Name;
import org.springframework.stereotype.Component;

@Component
public class UpdateAuthorMapper {
    public UpdatedAuthorResponse toResponse(Author author){
        return new UpdatedAuthorResponse(
                author.name().value(),
                author.surname().value()
        );
    }

}
