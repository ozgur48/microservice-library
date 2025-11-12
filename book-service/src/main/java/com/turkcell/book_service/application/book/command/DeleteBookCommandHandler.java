package com.turkcell.book_service.application.book.command;

import com.turkcell.book_service.application.book.dto.DeletedBookResponse;
import com.turkcell.book_service.cqrs.CommandHandler;
import com.turkcell.book_service.domain.exception.BookNotFoundException;
import com.turkcell.book_service.domain.model.Book;
import com.turkcell.book_service.domain.model.BookId;
import com.turkcell.book_service.domain.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteBookCommandHandler implements CommandHandler<DeleteBookCommand, DeletedBookResponse> {
    private final BookRepository bookRepository;

    public DeleteBookCommandHandler(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public DeletedBookResponse handle(DeleteBookCommand command) {
        BookId bookId = new BookId(command.bookId());
        bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException(command.bookId()));
        bookRepository.delete(bookId);
        return new DeletedBookResponse(command.bookId());
    }
}
