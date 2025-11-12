package com.turkcell.book_service.application.book.query;

import com.turkcell.book_service.application.book.dto.BookDetails;
import com.turkcell.book_service.cqrs.Query;

import java.util.UUID;

public record GetBookDetailsQuery(UUID id) implements Query<BookDetails> {
}
