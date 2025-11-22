package com.turkcell.fineservice.domain.event;

import com.turkcell.fineservice.domain.model.*;

import java.time.LocalDateTime;
import java.util.UUID;

public record FineCreatedEvent(
        UUID eventId,
        LocalDateTime occurredOn,
        FineId fineId,
        StaffId staffId,
        MemberId memberId,
        LoanId loanId,
        Amount amount) implements DomainEvent {

    public FineCreatedEvent(FineId fineId, StaffId staffId ,MemberId memberId, LoanId loanId, Amount amount){
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