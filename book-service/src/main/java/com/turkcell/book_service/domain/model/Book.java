package com.turkcell.book_service.domain.model;

import java.util.UUID;

public class Book {
    private final BookId bookId;

    private Title title;
    private Language language;
    private BookStatus bookStatus;
    private Year year;
    private TotalCopies totalCopies;
    private AvailableCopies availableCopies;
    private Isbn isbn;
    private UUID authorId;
    private UUID publisherId;

    private Book(BookId bookId, Title title, Language language, BookStatus bookStatus, Year year, TotalCopies totalCopies, AvailableCopies availableCopies, Isbn isbn, UUID authorId, UUID publisherId) {
        this.bookId = bookId;
        this.title = title;
        this.language = language;
        this.bookStatus = bookStatus;
        this.year = year;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.isbn = isbn;
        this.authorId = authorId;
        this.publisherId = publisherId;
    }
    public static Book create(Title title, Language language, BookStatus bookStatus, Year year, TotalCopies totalCopies, AvailableCopies availableCopies, Isbn isbn, UUID authorId, UUID publisherId){
        return new Book(BookId.generate(),
                title,
                language,
                bookStatus,
                year,
                totalCopies,
                availableCopies,
                isbn,
                authorId,
                publisherId
                );
    }
    public static Book rehydrate(BookId bookId, Title title,
                                 Language language, BookStatus bookStatus,
                                 Year year, TotalCopies totalCopies,
                                 AvailableCopies availableCopies, Isbn isbn,
                                 UUID authorId, UUID publisherId){
        return new Book(
                bookId,
                title,
                language,
                bookStatus,
                year,
                totalCopies,
                availableCopies,
                isbn,
                authorId,
                publisherId
        );
    }
    public void updateBookDetails(Title title, Language language, Year year){
       this.title = title;
       this.language = language;
       this.year = year;
    }
    public BookId bookId() {
        return bookId;
    }

    public Title title() {
        return title;
    }

    public Language language() {
        return language;
    }

    public BookStatus bookStatus() {
        return bookStatus;
    }

    public Year year() {
        return year;
    }

    public TotalCopies totalCopies() {
        return totalCopies;
    }

    public AvailableCopies availableCopies() {
        return availableCopies;
    }

    public Isbn isbn() {
        return isbn;
    }

    public UUID authorId() {
        return authorId;
    }

    public UUID publisherId() {
        return publisherId;
    }
}
