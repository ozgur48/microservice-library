package com.turkcell.book_service.application.book.mapper;

import com.turkcell.book_service.application.book.dto.BookDetails;
import com.turkcell.book_service.application.book.dto.BookStatusDto;
import com.turkcell.book_service.domain.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookDetailsMapper {
    public BookDetails toResponse(Book book){
        BookStatusDto status = BookStatusDto.valueOf(book.bookStatus().name());
        return new BookDetails(
                book.title().value(),
                book.language().value(),
                status,
                book.year().value(),
                book.totalCopies().copy(),
                book.availableCopies().copy(),
                book.isbn().value(),
                book.authorId(),
                book.publisherId()
        );
    }
}
