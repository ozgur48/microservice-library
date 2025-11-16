package com.turkcell.loanservice.domain.event;

import com.turkcell.loanservice.domain.model.LoanId;
import com.turkcell.loanservice.domain.model.MemberId;

import java.time.LocalDateTime;
import java.util.UUID;

public record LoanBecameOverdueEvent(UUID eventId, LocalDateTime occuredOn, LoanId loanId, MemberId memberId) implements DomainEvent {

    public LoanBecameOverdueEvent(LoanId loanId, MemberId memberId){
        this(UUID.randomUUID(), LocalDateTime.now() ,loanId, memberId);
    }

    @Override
    public UUID getEventId() {
        return null;
    }

    @Override
    public LocalDateTime getOccuredOn() {
        return null;
    }
}
