package com.turkcell.loanservice.domain.model;

import java.util.Objects;
import java.util.UUID;

public record StaffId(UUID value) {
    public StaffId{
        Objects.requireNonNull(value, "StaffId can not be null");
    }
}
