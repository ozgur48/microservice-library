package com.turkcell.loanservice.infrastructure.messaging.Outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.loanservice.domain.event.DomainEvent;
import com.turkcell.loanservice.domain.model.AggregateRoot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboxEventSaverImpl implements OutboxEventSaver {
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;


    public OutboxEventSaverImpl(OutboxRepository outboxRepository, ObjectMapper objectMapper) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void saveEvents(AggregateRoot aggregate) {
        List<DomainEvent> events = aggregate.getDomainEvents();
        for(DomainEvent event : events){
            try{// domain event' i JSON'A serileştirme
                String payloadJson = objectMapper.writeValueAsString(event);

                OutboxMessage message = new OutboxMessage(
                        event.getEventId(),                             // Domain Event'in benzersiz ID'si
                        aggregate.getClass().getSimpleName(),           // Örneğin: "Loan"
                        aggregate.getIdValue(),                         // Loan Aggregate'in UUID değeri
                        event.getClass().getName(),                     // Örneğin: "com.loanservice.events.BookReturnedEvent"
                        payloadJson
                );
                // 4. Outbox Tablosuna Kaydetme (Repository'yi kullanır)
                // Bu save işlemi, Application katmanındaki ana transaction'a katılır.
                outboxRepository.save(message);
            } catch (JsonProcessingException e){
                // Serileştirme hatası kritik bir iş hatasıdır ve Transaction'ı geri almalıdır (rollback).
                // Bu nedenle Unchecked Exception (Runtime) fırlatılır.
                throw new RuntimeException("Error serializing Domain Event for Outbox: " + event.getEventId(), e);
            }
        }
        // 5. Aggregate'i Temizleme
        // Olaylar artık kalıcı olarak Outbox tablosunda güvende olduğu için bellekteki listeyi temizle.
        aggregate.clearDomainEvents();
    }
}
