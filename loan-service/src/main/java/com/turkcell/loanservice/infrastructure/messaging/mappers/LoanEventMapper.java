package com.turkcell.loanservice.infrastructure.messaging.mappers;

import com.turkcell.loanservice.domain.event.LoanCreatedEvent;
import com.turkcell.loanservice.infrastructure.messaging.events.LoanCreatedIntegrationEvent;
import org.springframework.stereotype.Component;

@Component
public class LoanEventMapper {
    public LoanCreatedIntegrationEvent toIntegrationEvent(LoanCreatedEvent domainEvent){
        return new LoanCreatedIntegrationEvent(
                domainEvent.eventId(),
                domainEvent.occurredOn(),
                domainEvent.loanId().value(),
                domainEvent.memberId().value(),
                domainEvent.bookId().value()
        );
    }
}
