package com.turkcell.memberservice.domain.model;

import java.util.Objects;

public record Address(String value) {
    public Address{
        Objects.requireNonNull(value, "Address cannot be null");
        if(value.isBlank())
            throw new IllegalArgumentException("Value cannot be null");
    }
}
