package com.turkcell.author_service.messaging.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
@Configuration
public class BookCreatedConsumer {
    @Bean
    public Consumer<BookCreatedEvent> authorCreated(){
        return event -> {
            System.out.println("Yeni bir kitap oluşturuldu.");
            System.out.println(event.bookId);
        };
    }
    record BookCreatedEvent(String bookId){}
}
