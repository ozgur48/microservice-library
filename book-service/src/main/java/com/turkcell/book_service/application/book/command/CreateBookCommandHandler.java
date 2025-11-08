package com.turkcell.book_service.application.book.command;


import com.turkcell.book_service.application.book.dto.CreatedBookResponse;
import com.turkcell.book_service.application.book.mapper.CreateBookMapper;

import com.turkcell.book_service.cqrs.CommandHandler;
import com.turkcell.book_service.domain.model.Book;
import com.turkcell.book_service.domain.repository.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateBookCommandHandler implements CommandHandler<CreateBookCommand, CreatedBookResponse> {

    private final BookRepository bookRepository;
    private final CreateBookMapper createBookMapper;

    public CreateBookCommandHandler(BookRepository bookRepository, CreateBookMapper createBookMapper) {
        this.bookRepository = bookRepository;
        this.createBookMapper = createBookMapper;
    }

    @Override
    public CreatedBookResponse handle(CreateBookCommand command) {
        Book book = createBookMapper.toDomain(command);
        book = bookRepository.save(book);

        return createBookMapper.toResponse(book);
    }
}
