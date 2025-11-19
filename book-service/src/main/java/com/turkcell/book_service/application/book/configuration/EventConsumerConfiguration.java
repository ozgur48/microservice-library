package com.turkcell.book_service.application.book.configuration;

import com.turkcell.book_service.application.book.eventHandlers.BookLoanService;
import com.turkcell.book_service.messaging.events.LoanCreatedIntegrationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventConsumerConfiguration {
    private final BookLoanService bookLoanService;

    public EventConsumerConfiguration(BookLoanService bookLoanService) {
        this.bookLoanService = bookLoanService;
    }
    @Bean
    public Consumer<LoanCreatedIntegrationEvent> loanEvents(){
        return event -> {
            // Transaction ve iş mantığı, bu service içinde yürütülür.
            bookLoanService.processLoanCreation(event);
        };
    }
}
