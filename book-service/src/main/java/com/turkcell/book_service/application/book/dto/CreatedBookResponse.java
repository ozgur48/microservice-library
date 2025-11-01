package com.turkcell.book_service.application.book.dto;


import java.time.LocalDate;
import java.util.UUID;

public record CreatedBookResponse(UUID bookId,
                                  String title,
                                  String language,
                                  BookStatusDto bookStatus,
                                  LocalDate year,
                                  Integer totalCopies,
                                  Integer availableCopies,
                                  String isbn,
                                  UUID authorId,
                                  UUID publisherId) { }
