package com.turkcell.reservationservice.infrastructure.messaging.Outbox;

public enum OutboxStatus {
    PENDING,
    SENT,
    FAILED
}
