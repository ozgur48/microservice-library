package com.turkcell.loanservice.infrastructure.messaging.Outbox;


import com.turkcell.loanservice.domain.model.AggregateRoot;

public interface OutboxEventSaver {
    void saveEvents(AggregateRoot aggregate);
}
