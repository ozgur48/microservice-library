package com.turkcell.fineservice.domain.model;

import java.util.Objects;
import java.util.UUID;

public record MemberId(UUID value) {
    public MemberId{
        Objects.requireNonNull(value, "Value for memberId cannot be null");
    }
}
