package com.turkcell.book_service.domain.event;

import com.turkcell.book_service.domain.model.BookId;
import com.turkcell.book_service.domain.model.Title;

public record BookCreated(BookId bookId) {
    public BookCreated{
        if(bookId == null){
            throw new IllegalArgumentException("BookId cannot be null");
        }
    }
}
