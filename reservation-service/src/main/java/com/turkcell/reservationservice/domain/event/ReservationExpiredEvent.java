package com.turkcell.reservationservice.domain.event;

import com.turkcell.reservationservice.domain.model.ReservationId;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationExpiredEvent(
        UUID eventId,
        LocalDateTime occuredOn,
        ReservationId reservationId
) implements DomainEvent {

    public ReservationExpiredEvent(ReservationId reservationId){
        this(UUID.randomUUID(), LocalDateTime.now(), reservationId);
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
