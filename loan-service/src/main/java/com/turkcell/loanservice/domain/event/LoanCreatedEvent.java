package com.turkcell.loanservice.domain.event;

import com.turkcell.loanservice.domain.model.BookId;
import com.turkcell.loanservice.domain.model.LoanId;
import com.turkcell.loanservice.domain.model.MemberId;

import java.time.LocalDateTime;
import java.util.UUID;

public record LoanCreatedEvent(
        UUID eventId,
        LocalDateTime occurredOn,
        LoanId loanId,
        MemberId memberId,
        BookId bookId// Opsiyonel, ancak olayı kimin başlattığı bilgisi faydalı
) implements DomainEvent {

    // Helper Constructor (Command Handler tarafından çağrılacak)
    // ID'leri ve zamanı otomatik atar.
    public LoanCreatedEvent(LoanId loanId, MemberId memberId, BookId bookId) {
        this(UUID.randomUUID(), LocalDateTime.now(), loanId, memberId, bookId);
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