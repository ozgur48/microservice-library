package com.turkcell.reservationservice.domain.event;

import com.turkcell.reservationservice.domain.model.ReservationId;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationCancelledEvent(
        UUID eventId,
        LocalDateTime occuredOn,
        ReservationId reservationId,
        String reason
) implements DomainEvent {
    public ReservationCancelledEvent(ReservationId reservationId, String reason){
        this(UUID.randomUUID(), LocalDateTime.now(), reservationId, reason);
    }
    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public LocalDateTime getOccuredOn() {
        return occuredOn;
    }
}
