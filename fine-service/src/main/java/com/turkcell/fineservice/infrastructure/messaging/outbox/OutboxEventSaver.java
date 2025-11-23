package com.turkcell.fineservice.infrastructure.messaging.outbox;

import com.turkcell.fineservice.domain.model.AggregateRoot;

public interface OutboxEventSaver {
    void saveEvents(AggregateRoot aggregateRoot);
}
