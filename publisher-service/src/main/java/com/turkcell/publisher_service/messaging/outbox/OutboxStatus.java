package com.turkcell.publisher_service.messaging.outbox;

public enum OutboxStatus {
    PENDING,
    SENT,
    FAILED
}