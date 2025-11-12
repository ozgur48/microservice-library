package com.turkcell.book_service.application.book.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UpdatedBookResponse(String title,
                                  String language,
                                  BookStatusDto bookStatus,
                                  LocalDate year,
                                  Integer totalCopies,
                                  Integer availableCopies,
                                  String isbn,
                                  UUID authorId,
                                  UUID publisherId) {
}
