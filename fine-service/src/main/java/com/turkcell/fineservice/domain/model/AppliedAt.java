package com.turkcell.fineservice.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record AppliedAt(LocalDateTime value) {
    public AppliedAt{
        Objects.requireNonNull(value, "AppliedAt can not be null");
    }
    public static AppliedAt now(){
        return new AppliedAt(LocalDateTime.now());
    }
}
