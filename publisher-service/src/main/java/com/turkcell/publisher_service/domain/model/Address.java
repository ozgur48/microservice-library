package com.turkcell.publisher_service.domain.model;

import java.util.Objects;

public record Address(String value) {
    public Address{
        Objects.requireNonNull(value, "Address null olamaz");

        if(value.length() >= 255)
            throw new IllegalArgumentException("255 karakterden fazla olamaz");
        if(value.isBlank())
            throw new IllegalArgumentException("Boş olamaz");
    }
}