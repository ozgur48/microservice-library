package com.turkcell.book_service.domain.model;

import com.turkcell.book_service.domain.event.BookStatusChangedEvent;
import com.turkcell.book_service.domain.event.DomainEvent;
import com.turkcell.book_service.domain.exception.BookNotAvailableForLoanException;
import com.turkcell.book_service.domain.exception.BookOutOfStockException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Book implements AggregateRoot{
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

    private LoanId currentLoanId;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

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
    public void markAsOnLoan(UUID loanId){
        // 1 stok kontrolü
        if(this.availableCopies.copy() <= 0){
            throw new BookOutOfStockException(this.bookId.value(), this.availableCopies.copy());
        }
        // 2. domain kuralı kontrolü
        if(this.bookStatus != BookStatus.AVAILABLE ){
            throw new BookNotAvailableForLoanException(this.bookId.value(), this.bookStatus);
        }
        // 3 kopya sayısı güncellenir
        this.availableCopies = this.availableCopies.decrement();

        // 4. durum değişimi
        BookStatus oldStatus = this.bookStatus;

        // Kopya sayısı 1'den 0'a düştüyse (yani artık stok kalmadıysa) genel durumu CHECKED_OUT yaparız.
        // Eğer stok hala > 0 ise, durum AVAILABLE kalmalıdır.
        if(this.availableCopies.copy() == 0){
            this.bookStatus = BookStatus.CHECKED_OUT;
        }

        this.currentLoanId = new LoanId(loanId);

        // 5.domain evet kaydı
        this.domainEvents.add(new BookStatusChangedEvent
                (this.bookId, oldStatus, this.bookStatus)
        );

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
    @Override
    // olayları okumak için
    public List<DomainEvent> getDomainEvents(){
        return Collections.unmodifiableList(domainEvents);
    }
    @Override
    public void clearDomainEvents(){
        this.domainEvents.clear();
    }

    @Override
    public UUID getIdValue() {
        return this.currentLoanId.value();
    }
}
