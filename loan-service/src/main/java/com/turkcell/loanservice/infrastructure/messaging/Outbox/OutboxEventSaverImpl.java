package com.turkcell.loanservice.infrastructure.messaging.Outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.loanservice.domain.event.DomainEvent;
import com.turkcell.loanservice.domain.event.LoanCreatedEvent;
import com.turkcell.loanservice.domain.model.AggregateRoot;
import com.turkcell.loanservice.infrastructure.messaging.mappers.LoanEventMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboxEventSaverImpl implements OutboxEventSaver {
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;
    private final LoanEventMapper loanEventMapper;


    public OutboxEventSaverImpl(OutboxRepository outboxRepository, ObjectMapper objectMapper, LoanEventMapper loanEventMapper) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
        this.loanEventMapper = loanEventMapper;
    }

    @Override
    public void saveEvents(AggregateRoot aggregate) {
        List<DomainEvent> events = aggregate.getDomainEvents();
        for(DomainEvent event : events){
            Object integrationEvent;
            String eventTyoeForOutbox;
            // gelen olay tipine göre dönüşüm yap
            if(event instanceof LoanCreatedEvent createdEvent){
                // sadece loancreatedevent geldiğinde dönüşüm yaplır
                integrationEvent = loanEventMapper.toIntegrationEvent(createdEvent);
                eventTyoeForOutbox = createdEvent.getClass().getName();
            }
            // VEYA DİĞER OLAY TİPLERİ İÇİN EKLEYİN:
            /*
            else if (event instanceof LoanDueDateExtendedEvent extendedEvent) {
                integrationEvent = loanEventMapper.toIntegrationEvent(extendedEvent);
                eventTypeForOutbox = extendedEvent.getClass().getName();
            }
            */
            else{
                // Olayın Integration Event'e karşılığı yoksa, bu bir altyapı hatasıdır.
                // Transaction'ı geri al
                throw new RuntimeException("Domain Event type not mapped for Outbox serialization: " + event.getClass().getName());
            }
            try{
                // 2.serileştirme
                String payloadJson = objectMapper.writeValueAsString(integrationEvent);

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
