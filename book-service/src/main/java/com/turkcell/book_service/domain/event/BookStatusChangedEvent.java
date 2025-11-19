package com.turkcell.book_service.domain.event;

import com.turkcell.book_service.domain.model.BookId;
import com.turkcell.book_service.domain.model.BookStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookStatusChangedEvent(
        UUID eventId,
        LocalDateTime occurredOn,
        BookId bookId,
        BookStatus oldStatus,
        BookStatus newStatus
) implements DomainEvent {
    public BookStatusChangedEvent(BookId bookId, BookStatus oldStatus, BookStatus newStatus) {
        this(UUID.randomUUID(), LocalDateTime.now(), bookId, oldStatus, newStatus);
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
