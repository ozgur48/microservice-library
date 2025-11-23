package com.turkcell.fineservice.infrastructure.messaging.events;

import com.turkcell.fineservice.domain.event.DomainEvent;
import com.turkcell.fineservice.domain.model.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record FineCreatedIntegrationEvent(
        UUID eventId,
        LocalDateTime occurredOn,
        UUID fineId,
        UUID staffId,
        UUID memberId,
        UUID loanId,
        BigDecimal amount) implements DomainEvent {

    public FineCreatedIntegrationEvent(UUID fineId, UUID staffId ,UUID memberId, UUID loanId, BigDecimal amount){
        this(UUID.randomUUID(), LocalDateTime.now(), fineId, staffId, memberId, loanId, amount);
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public LocalDateTime getOccuredOn() {
        return occurredOn;
    }
}