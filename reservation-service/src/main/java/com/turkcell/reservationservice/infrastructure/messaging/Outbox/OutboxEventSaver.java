package com.turkcell.reservationservice.infrastructure.messaging.Outbox;

import com.turkcell.reservationservice.domain.model.AggregateRoot;

public interface OutboxEventSaver {
    void saveEvents(AggregateRoot aggregate);
}
