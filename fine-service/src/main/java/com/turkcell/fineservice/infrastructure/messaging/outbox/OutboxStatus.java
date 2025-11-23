package com.turkcell.fineservice.infrastructure.messaging.outbox;

public enum OutboxStatus {
    PENDING,
    SENT,
    FAILED
}
