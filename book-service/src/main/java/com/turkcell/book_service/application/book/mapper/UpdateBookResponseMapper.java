package com.turkcell.book_service.application.book.mapper;

import com.turkcell.book_service.application.book.dto.BookStatusDto;
import com.turkcell.book_service.application.book.dto.UpdatedBookResponse;
import com.turkcell.book_service.domain.model.Book;
import org.springframework.stereotype.Component;

@Component
public class UpdateBookResponseMapper {
    public UpdatedBookResponse toResponse(Book book){
        var statusDto = BookStatusDto.valueOf(book.bookStatus().name());
        return new UpdatedBookResponse(
                book.title().value(),
                book.language().value(),
                statusDto,
                book.year().value(),
                book.totalCopies().copy(),
                book.availableCopies().copy(),
                book.isbn().value(),
                book.authorId(),
                book.publisherId()
        );
    }
}
