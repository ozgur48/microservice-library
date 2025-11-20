package com.turkcell.book_service.configuration;

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
    // Bean metodu, Spring Cloud Stream için Kafka Consumer fonksiyonudur.
    @Bean
    public Consumer<LoanCreatedIntegrationEvent> loanEvents(){
        return event -> {
            try{
                bookLoanService.processLoanCreation(event);
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("Consumer not work", e);
            }
        };
    }
}
