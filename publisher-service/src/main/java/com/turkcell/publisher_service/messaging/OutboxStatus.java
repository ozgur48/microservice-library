package com.turkcell.publisher_service.messaging;

public enum OutboxStatus {
    PENDING,
    SENT,
    FAILED
}