package com.turkcell.book_service.domain.event;

import com.turkcell.book_service.domain.model.BookId;

public record BookCreateFailed(BookId bookId) {
    public BookCreateFailed{
        if(bookId == null){
            throw new IllegalArgumentException("bookId cannot be null");
        }
    }
}
