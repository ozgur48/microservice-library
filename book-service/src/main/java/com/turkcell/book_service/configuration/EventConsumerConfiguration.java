package com.turkcell.book_service.configuration;

import com.turkcell.book_service.application.book.eventHandlers.BookLoanService;
import com.turkcell.book_service.messaging.events.LoanCreatedIntegrationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class EventConsumerConfiguration {
    private final BookLoanService bookLoanService;
    private static final Logger log = LoggerFactory.getLogger(EventConsumerConfiguration.class);

    public EventConsumerConfiguration(BookLoanService bookLoanService) {
        this.bookLoanService = bookLoanService;
    }
    // Bean metodu, Spring Cloud Stream için Kafka Consumer fonksiyonudur.
    @Bean
    public Consumer<LoanCreatedIntegrationEvent> loanEvents(){
        return event -> {
            log.info("📥 LoanCreatedIntegrationEvent RECEIVED in book-service: {}", event);
            // Bu şekilde çağrı, Spring'in transactional vekilini kullanmasını garanti eder.
            bookLoanService.processLoanCreation(event);
        };
    }
}
