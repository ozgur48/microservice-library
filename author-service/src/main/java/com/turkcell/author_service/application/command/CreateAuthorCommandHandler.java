package com.turkcell.author_service.application.command;

import com.turkcell.author_service.application.dto.CreatedAuthorResponse;
import com.turkcell.author_service.application.mapper.CreateAuthorMapper;
import com.turkcell.author_service.core.cqrs.CommandHandler;
import com.turkcell.author_service.domain.model.Author;
import com.turkcell.author_service.domain.repository.AuthorRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateAuthorCommandHandler implements CommandHandler<CreateAuthorCommand, CreatedAuthorResponse> {
    private final AuthorRepository authorRepository;
    private final CreateAuthorMapper createAuthorMapper;

    public CreateAuthorCommandHandler(AuthorRepository authorRepository, CreateAuthorMapper createAuthorMapper) {
        this.authorRepository = authorRepository;
        this.createAuthorMapper = createAuthorMapper;
    }

    @Override
    public CreatedAuthorResponse handle(CreateAuthorCommand command) {
        Author author = createAuthorMapper.toDomain(command);
        author = authorRepository.save(author);
        return createAuthorMapper.toResponse(author);
    }
}
