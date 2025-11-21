package com.turkcell.reservationservice.infrastructure.messaging.Outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.reservationservice.domain.event.DomainEvent;
import com.turkcell.reservationservice.domain.event.ReservationCreatedEvent;
import com.turkcell.reservationservice.domain.model.AggregateRoot;
import com.turkcell.reservationservice.infrastructure.messaging.Mappers.ReservationEventMapper;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class OutboxEventSaverImpl  implements OutboxEventSaver {
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;
    private final ReservationEventMapper reservationEventMapper;

    public OutboxEventSaverImpl(OutboxRepository outboxRepository, ObjectMapper objectMapper, ReservationEventMapper reservationEventMapper) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
        this.reservationEventMapper = reservationEventMapper;
    }

    @Override
    public void saveEvents(AggregateRoot aggregate) {
        List<DomainEvent> events = aggregate.getDomainEvents();
        for(DomainEvent event : events){
            Object integrationEvent;
            String eventTypeForOutbox;

            if(event instanceof ReservationCreatedEvent createdEvent){
                integrationEvent = reservationEventMapper.toIntegrationEvent(createdEvent);
                eventTypeForOutbox = createdEvent.getClass().getName();
            }
            // VEYA DİĞER OLAY TİPLERİ İÇİN EKLEYİN:
            /*
            else if (event instanceof LoanDueDateExtendedEvent extendedEvent) {
                integrationEvent = loanEventMapper.toIntegrationEvent(extendedEvent);
                eventTypeForOutbox = extendedEvent.getClass().getName();
            }
            */
            else {
                // Olayın Integration Event'e karşılığı yoksa, bu bir altyapı hatasıdır.
                // Transaction'ı geri al
                throw new RuntimeException("Domain Event type not mapped for Outbox serialization: " + event.getClass().getName());
            }
            try{
                // 2. serileştirme
                String payloadJson = objectMapper.writeValueAsString(integrationEvent);

                OutboxMessage message = new OutboxMessage(
                        event.getEventId(),                             // Domain Event'in benzersiz ID'si
                        aggregate.getClass().getSimpleName(),           // Örneğin: "Loan"
                        aggregate.getIdValue(),                         // Loan Aggregate'in UUID değeri
                        event.getClass().getName(),                     // Örneğin: "com.loanservice.events.BookReturnedEvent"
                        payloadJson
                );

                // outbox tablosuna kaydet
                outboxRepository.save(message);
            }catch(JsonProcessingException e){
                // Serileştirme hatası kritik bir iş hatasıdır ve Transaction'ı geri almalıdır (rollback).
                // Bu nedenle Unchecked Exception (Runtime) fırlatılır.
                throw new RuntimeException("Error serializing Domain Event for Outbox: " + event.getEventId(), e);
            }
        }
        // 5. aggregat temizle
        // 5. Aggregate'i Temizleme
        // Olaylar artık kalıcı olarak Outbox tablosunda güvende olduğu için bellekteki listeyi temizle.
        aggregate.clearDomainEvents();
    }
}
