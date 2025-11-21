package com.turkcell.book_service.messaging.consumer;

import com.turkcell.book_service.application.book.eventHandlers.BookLoanService;
import com.turkcell.book_service.messaging.events.LoanCreatedIntegrationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class LoanCreatedEventConsumer {
    private final BookLoanService bookLoanService;

    public LoanCreatedEventConsumer(BookLoanService bookLoanService) {
        this.bookLoanService = bookLoanService;
    }

    @Bean
    public Consumer<LoanCreatedIntegrationEvent> loanEvents(){
        return  event -> {
            bookLoanService.processLoanCreation(event);
        };
    }
}
