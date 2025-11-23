package com.turkcell.fineservice.infrastructure.messaging.mapper;

import com.turkcell.fineservice.domain.event.FineCreatedEvent;
import com.turkcell.fineservice.infrastructure.messaging.events.FineCreatedIntegrationEvent;
import org.springframework.stereotype.Component;

@Component
public class FineEventMapper {
    public FineCreatedIntegrationEvent toIntegrationEvent(FineCreatedEvent domainEvent){
        return new FineCreatedIntegrationEvent(
                domainEvent.eventId(),
                domainEvent.occurredOn(),
                domainEvent.fineId().value(),
                domainEvent.staffId().value(),
                domainEvent.memberId().value(),
                domainEvent.loanId().value(),
                domainEvent.amount().value()
        );
    }
}
