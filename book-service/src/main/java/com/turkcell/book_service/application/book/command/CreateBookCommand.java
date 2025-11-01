package com.turkcell.book_service.application.book.command;


import com.turkcell.book_service.application.book.dto.BookStatusDto;
import com.turkcell.book_service.application.book.dto.CreatedBookResponse;
import com.turkcell.book_service.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CreateBookCommand(
                                @NotBlank String title,
                                @NotBlank String language,
                                @NotNull BookStatusDto bookStatus,
                                @NotNull LocalDate year,
                                @NotNull Integer totalCopies,
                                @NotNull Integer availableCopies,
                                @NotBlank String isbn,
                                @NotNull UUID authorId,
                                @NotNull UUID publisherId)implements Command<CreatedBookResponse> {
}
