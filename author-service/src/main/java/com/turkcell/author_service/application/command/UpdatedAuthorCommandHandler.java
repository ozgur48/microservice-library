package com.turkcell.author_service.application.command;

import com.turkcell.author_service.application.dto.UpdatedAuthorResponse;
import com.turkcell.author_service.application.mapper.UpdateAuthorMapper;
import com.turkcell.author_service.core.cqrs.CommandHandler;
import com.turkcell.author_service.domain.exception.AuthorNotFoundException;
import com.turkcell.author_service.domain.model.Author;
import com.turkcell.author_service.domain.model.AuthorId;
import com.turkcell.author_service.domain.model.Name;
import com.turkcell.author_service.domain.model.Surname;
import com.turkcell.author_service.domain.repository.AuthorRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdatedAuthorCommandHandler implements CommandHandler<UpdateAuthorCommand, UpdatedAuthorResponse> {
    private final AuthorRepository authorRepository;
    private final UpdateAuthorMapper updateAuthorMapper;

    public UpdatedAuthorCommandHandler(AuthorRepository authorRepository, UpdateAuthorMapper updateAuthorMapper) {
        this.authorRepository = authorRepository;
        this.updateAuthorMapper = updateAuthorMapper;
    }


    @Override
    public UpdatedAuthorResponse handle(UpdateAuthorCommand command) {
        Author author = authorRepository.findById(new AuthorId(command.authorId()))
                .orElseThrow(()-> new AuthorNotFoundException(command.authorId()));
        Name name = new Name(command.name());
        Surname surname = new Surname(command.surname());
        // burada 2 farklı methodla güncelledim
        //author.reName(name);
        //author.reSurName(surname);
        // burada update methoduyla
        author.updateAuthor(name, surname);
        authorRepository.save(author);
        return updateAuthorMapper.toResponse(author);
    }
}
