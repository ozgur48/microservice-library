package com.turkcell.book_service.application.book.query;

import com.turkcell.book_service.application.book.dto.BookDetails;
import com.turkcell.book_service.application.book.mapper.BookDetailsMapper;
import com.turkcell.book_service.cqrs.QueryHandler;
import com.turkcell.book_service.domain.exception.BookNotFoundException;
import com.turkcell.book_service.domain.model.Book;
import com.turkcell.book_service.domain.model.BookId;
import com.turkcell.book_service.domain.repository.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class GetBookDetailsQueryHandler implements QueryHandler<GetBookDetailsQuery, BookDetails> {
    private final BookRepository bookRepository;
    private final BookDetailsMapper bookDetailsMapper;

    public GetBookDetailsQueryHandler(BookRepository bookRepository, BookDetailsMapper bookDetailsMapper) {
        this.bookRepository = bookRepository;
        this.bookDetailsMapper = bookDetailsMapper;
    }

    @Override
    public BookDetails handle(GetBookDetailsQuery query) {
        BookId bookId = new BookId(query.id());
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException(query.id()));
        return bookDetailsMapper.toResponse(book);
    }
}
