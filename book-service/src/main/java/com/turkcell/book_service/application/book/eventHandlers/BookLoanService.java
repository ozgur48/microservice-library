package com.turkcell.book_service.application.book.eventHandlers;

import com.turkcell.book_service.domain.exception.BookNotFoundException;
import com.turkcell.book_service.domain.model.Book;
import com.turkcell.book_service.domain.model.BookId;
import com.turkcell.book_service.domain.repository.BookRepository;
import com.turkcell.book_service.messaging.events.LoanCreatedIntegrationEvent;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class BookLoanService {
    private final BookRepository bookRepository;

    public BookLoanService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional // book kaydı sırasında hata olursa rollback yapar
    public void processLoanCreation(LoanCreatedIntegrationEvent event){
        BookId bookId = new BookId(event.bookId()); // gelen uuid'yi vo'ye çevirir

        //Aggregate'i yükle
        Book book = bookRepository.findById(bookId).orElseThrow(()-> new BookNotFoundException(bookId));

        // domain davraşını çağır
        book.markAsOnLoan(event.loanId());

        // değişiklikleri kalıcı hale getir
        bookRepository.save(book);

        // Gerekirse, bu event'i dışarıya yayınlamak için Outbox'a kaydet.
        // EĞER book-service başka servise event yayınlayacaksa:
        // bookOutboxEventSaver.saveEvents(book); // <-- Bu çağrı eklenmelidir.

    }

}
