package com.turkcell.loanservice.domain.event;

import com.turkcell.loanservice.domain.model.BookId;
import com.turkcell.loanservice.domain.model.LoanId;
import com.turkcell.loanservice.domain.model.MemberId;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookReturnedEvent(UUID eventId, LocalDateTime occuredOn, LoanId loanId, MemberId memberId, BookId bookId)
        implements DomainEvent {


    public BookReturnedEvent(LoanId loanId, MemberId memberId, BookId bookId){
        this(UUID.randomUUID(), LocalDateTime.now(), loanId, memberId, bookId);
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
