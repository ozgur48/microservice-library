package com.turkcell.book_service.domain.exception;

import com.turkcell.book_service.domain.model.AvailableCopies;

import java.util.UUID;

public class BookOutOfStockException extends RuntimeException {
    public BookOutOfStockException(UUID bookId, int currentStock) {
        super(String.format(
                "Book (ID: %s) is currently out of stock %s and cannot be marked as ON_LOAN.",
                bookId.toString(),
                currentStock
        ));
    }
}
