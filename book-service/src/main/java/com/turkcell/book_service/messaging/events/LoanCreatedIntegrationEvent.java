package com.turkcell.book_service.messaging.events;


import java.time.LocalDateTime;
import java.util.UUID;

public record LoanCreatedIntegrationEvent(
        UUID eventId,
        LocalDateTime occurredOn,
        UUID loanId,
        UUID memberId,
        UUID bookId// Opsiyonel, ancak olayı kimin başlattığı bilgisi faydalı
) {

    // Helper Constructor (Command Handler tarafından çağrılacak)
    // ID'leri ve zamanı otomatik atar.
    public LoanCreatedIntegrationEvent(UUID loanId, UUID memberId, UUID bookId) {
        this(UUID.randomUUID(), LocalDateTime.now(), loanId, memberId, bookId);
    }

}

