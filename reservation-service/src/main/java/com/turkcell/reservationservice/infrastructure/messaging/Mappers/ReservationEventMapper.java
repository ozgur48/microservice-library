package com.turkcell.reservationservice.infrastructure.messaging.Mappers;

import com.turkcell.reservationservice.domain.event.ReservationCreatedEvent;
import com.turkcell.reservationservice.infrastructure.messaging.Events.ReservationCreatedIntegrationEvent;
import org.springframework.stereotype.Component;

@Component
public class ReservationEventMapper {
    public ReservationCreatedIntegrationEvent toIntegrationEvent(ReservationCreatedEvent domainEvent){
        return new ReservationCreatedIntegrationEvent(
                domainEvent.eventId(),
                domainEvent.occuredOn(),
                domainEvent.reservationId().value(),
                domainEvent.bookId().value(),
                domainEvent.memberId().value(),
                domainEvent.reservedAt(),
                domainEvent.expireAt()
        );
    }
}
