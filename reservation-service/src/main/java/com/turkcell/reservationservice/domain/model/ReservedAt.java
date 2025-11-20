package com.turkcell.reservationservice.domain.model;

import java.time.LocalDateTime;

public record ReservedAt(LocalDateTime value) {
    public ReservedAt{
        if(value == null)
            throw new IllegalArgumentException("ReservedAt can not be null");
    }
}
