package com.turkcell.reservationservice.domain.model;

import java.time.LocalDateTime;

public record ExpireAt(LocalDateTime value) {
    public ExpireAt{
        if(value == null)
            throw new IllegalArgumentException("ExpireAt can not be null");
    }
}
