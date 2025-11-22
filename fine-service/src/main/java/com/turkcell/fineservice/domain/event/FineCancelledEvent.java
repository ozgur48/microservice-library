package com.turkcell.fineservice.domain.event;

import com.turkcell.fineservice.domain.model.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record FineCancelledEvent(
        UUID eventId,
        LocalDateTime occurredOn,
        FineId fineId,
        StaffId staffId,
        MemberId memberId,
        Reason reason) implements DomainEvent {
    public FineCancelledEvent(FineId fineId, StaffId staffId ,MemberId memberId, Reason reason){
        this(UUID.randomUUID(), LocalDateTime.now(), fineId, staffId, memberId, reason);
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
