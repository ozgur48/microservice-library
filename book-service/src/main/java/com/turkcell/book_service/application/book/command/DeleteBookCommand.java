package com.turkcell.book_service.application.book.command;

import com.turkcell.book_service.application.book.dto.DeletedBookResponse;
import com.turkcell.book_service.cqrs.Command;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DeleteBookCommand(@NotNull UUID bookId) implements Command<DeletedBookResponse> {
}
