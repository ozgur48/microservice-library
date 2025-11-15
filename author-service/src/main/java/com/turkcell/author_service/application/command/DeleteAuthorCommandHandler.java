package com.turkcell.author_service.application.command;

import com.turkcell.author_service.application.dto.DeletedAuthorResponse;
import com.turkcell.author_service.core.cqrs.CommandHandler;
import com.turkcell.author_service.domain.exception.AuthorNotFoundException;
import com.turkcell.author_service.domain.model.Author;
import com.turkcell.author_service.domain.model.AuthorId;
import com.turkcell.author_service.domain.repository.AuthorRepository;

public class DeleteAuthorCommandHandler implements CommandHandler<DeleteAuthorCommand, DeletedAuthorResponse> {
    private final AuthorRepository authorRepository;

    public DeleteAuthorCommandHandler(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public DeletedAuthorResponse handle(DeleteAuthorCommand command) {
        AuthorId authorId = new AuthorId(command.id());
        authorRepository.findById(authorId)
                .orElseThrow(()-> new AuthorNotFoundException(command.id()));
        authorRepository.delete(authorId);
        return new DeletedAuthorResponse(command.id());
    }
}
