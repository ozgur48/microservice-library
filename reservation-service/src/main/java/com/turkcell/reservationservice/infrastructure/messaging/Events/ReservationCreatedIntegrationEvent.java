package com.turkcell.reservationservice.infrastructure.messaging.Events;

import com.turkcell.reservationservice.domain.event.DomainEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationCreatedIntegrationEvent(
        UUID eventId,
        LocalDateTime occurredOn,
        UUID reservationId,
        UUID bookId,
        UUID memberId,
        LocalDateTime reservedAt,
        LocalDateTime expireAt) implements DomainEvent {
    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public LocalDateTime getOccuredOn() {
        return occurredOn;
    }
}
