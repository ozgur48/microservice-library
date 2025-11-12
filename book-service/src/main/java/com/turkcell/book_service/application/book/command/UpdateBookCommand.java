package com.turkcell.book_service.application.book.command;

import com.turkcell.book_service.application.book.dto.BookStatusDto;
import com.turkcell.book_service.application.book.dto.UpdatedBookResponse;
import com.turkcell.book_service.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateBookCommand(@NotNull UUID bookId,
                                @NotBlank String title,
                                @NotBlank String language,
                                @NotNull LocalDate year) implements Command<UpdatedBookResponse> {
}
