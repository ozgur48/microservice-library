package com.turkcell.book_service.domain.exception;

import com.turkcell.book_service.domain.model.BookId;

import java.util.UUID;

public class BookNotFoundException extends RuntimeException{
    // uuid ve BookId value object'ini alır
    public BookNotFoundException(UUID  bookId){
        super("Book, ID: " + bookId + "could not find.!");
    }
    public  BookNotFoundException(BookId bookId){
        super("Book, ID: " + bookId.value() + "could not find.!");
    }
}
