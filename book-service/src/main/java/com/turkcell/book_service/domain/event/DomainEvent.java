package com.turkcell.book_service.domain.event;


import java.time.LocalDateTime;
import java.util.UUID;

public interface DomainEvent {
    UUID getEventId();
    LocalDateTime getOccuredOn();
}

