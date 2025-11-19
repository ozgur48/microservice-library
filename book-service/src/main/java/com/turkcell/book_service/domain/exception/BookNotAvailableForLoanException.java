package com.turkcell.book_service.domain.exception;

import com.turkcell.book_service.domain.model.BookStatus;

import java.util.UUID;

public class BookNotAvailableForLoanException extends IllegalArgumentException {
    public BookNotAvailableForLoanException(UUID bookId, BookStatus currentStatus) {
        super(String.format(
                "Book (ID: %s) is currently in %s status and cannot be marked as ON_LOAN.",
                bookId.toString(),
                currentStatus.name()
        ));
    }
}
