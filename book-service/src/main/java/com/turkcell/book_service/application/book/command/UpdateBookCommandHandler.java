package com.turkcell.book_service.application.book.command;

import com.turkcell.book_service.application.book.dto.UpdatedBookResponse;
import com.turkcell.book_service.application.book.mapper.UpdateBookResponseMapper;
import com.turkcell.book_service.cqrs.CommandHandler;
import com.turkcell.book_service.domain.exception.BookNotFoundException;
import com.turkcell.book_service.domain.model.Book;
import com.turkcell.book_service.domain.model.BookId;
import com.turkcell.book_service.domain.model.*;
import com.turkcell.book_service.domain.repository.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateBookCommandHandler implements CommandHandler<UpdateBookCommand, UpdatedBookResponse> {
    private final BookRepository bookRepository;
    private final UpdateBookResponseMapper updateBookResponseMapper;

    public UpdateBookCommandHandler(BookRepository bookRepository, UpdateBookResponseMapper updateBookResponseMapper) {
        this.bookRepository = bookRepository;
        this.updateBookResponseMapper = updateBookResponseMapper;
    }

    @Override
    public UpdatedBookResponse handle(UpdateBookCommand command) {
        Book book = bookRepository.findById(new BookId(command.bookId()))
                .orElseThrow(()-> new BookNotFoundException(command.bookId()));
        Title newTitle = new Title(command.title());
        Language newLanguage = new Language(command.language());
        Year newYear = new Year(command.year());
        book.updateBookDetails(newTitle, newLanguage, newYear);
        bookRepository.save(book);
        return updateBookResponseMapper.toResponse(book);
    }
}
