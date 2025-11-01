package com.turkcell.book_service.infrastructure.book;

import com.turkcell.book_service.domain.model.BookStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Table(name="books")
@Entity
public class JpaBookEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    @Size(max = 150)
    private String title;

    @Column(nullable = false)
    private LocalDate year;

    @Column(nullable = false)

    @Size(max = 150)
    private String language;

    @Column(name = "total_copies", nullable = false)
    @Positive
    private int totalCopies;

    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_status", nullable = false)
    private BookStatus bookStatus;

    @Column(nullable = false)
    @Positive
    private int availableCopies;


    @Column(nullable = false)
    private UUID authorId;

    @Column(nullable = false)
    private UUID publisherId;

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String title() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate year() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public String language() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int totalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public String isbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookStatus bookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public int availableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public UUID authorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public UUID publisherId() {
        return publisherId;
    }

    public void setPublisherId(UUID publisherId) {
        this.publisherId = publisherId;
    }
}
