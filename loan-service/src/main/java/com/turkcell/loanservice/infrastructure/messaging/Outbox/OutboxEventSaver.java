package com.turkcell.loanservice.infrastructure.messaging.Outbox;


public interface OutboxEventSaver {
    void saveEvents(AggregateRoot aggregate);
}
