package com.turkcell.fineservice.infrastructure.messaging.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.fineservice.domain.event.DomainEvent;
import com.turkcell.fineservice.domain.event.FineCreatedEvent;
import com.turkcell.fineservice.domain.model.AggregateRoot;
import com.turkcell.fineservice.infrastructure.messaging.mapper.FineEventMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutboxEventSaverImpl implements OutboxEventSaver {

    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;
    private final FineEventMapper fineEventMapper;

    public OutboxEventSaverImpl(OutboxRepository outboxRepository, ObjectMapper objectMapper,
            FineEventMapper fineEventMapper) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
        this.fineEventMapper = fineEventMapper;
    }

    @Override
    public void saveEvents(AggregateRoot aggregate) {
        List<DomainEvent> events = aggregate.getDomainEvents();
        for (DomainEvent event : events) {
            Object integrationEvent;
            String eventTypeForOutbox;
            if (event instanceof FineCreatedEvent fineCreatedEvent) {
                // sadece finecreateevent için dönüşüm yapılır
                integrationEvent = fineEventMapper.toIntegrationEvent(fineCreatedEvent);
                eventTypeForOutbox = fineCreatedEvent.getClass().getName();
            } else {
                throw new RuntimeException(
                        "Domain Event type not mapped for Outbox serialization: " + event.getClass().getName());
            }
            try {
                String payloadJson = objectMapper.writeValueAsString(integrationEvent);
                OutboxMessage message = new OutboxMessage(
                        event.getEventId(),
                        aggregate.getClass().getSimpleName(),
                        aggregate.getIdValue(),
                        event.getClass().getName(),
                        payloadJson
                );
                outboxRepository.save(message);
            } catch (JsonProcessingException e) {
                // Serileştirme hatası kritik bir iş hatasıdır ve Transaction'ı geri almalıdır
                // (rollback).
                // Bu nedenle Unchecked Exception (Runtime) fırlatılır.
                throw new RuntimeException("Error serializing Domain Event for Outbox: " + event.getEventId(), e);
            }
        }
        // Aggregate'i Temizleme
        // Olaylar artık kalıcı olarak Outbox tablosunda güvende olduğu için bellekteki
        // listeyi temizle.
        aggregate.clearDomainEvents();
    }
}
