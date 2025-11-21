package com.turkcell.reservationservice.domain.event;

import com.turkcell.reservationservice.domain.model.BookId;
import com.turkcell.reservationservice.domain.model.MemberId;
import com.turkcell.reservationservice.domain.model.ReservationId;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReservationCreatedEvent(
        UUID eventId,
        LocalDateTime occuredOn,
        ReservationId reservationId,
        BookId bookId,
        MemberId memberId,
        LocalDateTime reservedAt,
        LocalDateTime expireAt

) implements DomainEvent {
    public ReservationCreatedEvent(ReservationId reservationId, BookId bookId,
                                   MemberId memberId, LocalDateTime reservedAt,
                                   LocalDateTime expireAt){
        this(UUID.randomUUID(), LocalDateTime.now(), reservationId, bookId,
                memberId, reservedAt, expireAt);
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
