package com.turkcell.loanservice.domain.event;

import com.turkcell.loanservice.domain.model.BookId;
import com.turkcell.loanservice.domain.model.LoanId;

import java.time.LocalDateTime;
import java.util.UUID;

public record LoanDueDateExtendedEvent(UUID eventId, LocalDateTime occuredOn, LoanId loanId, BookId bookId,
                                       LocalDateTime newDueDate) implements DomainEvent {

    public LoanDueDateExtendedEvent(LoanId loanId, BookId bookId, LocalDateTime newDueDate){
        this(UUID.randomUUID(), LocalDateTime.now(), loanId, bookId, newDueDate);
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
