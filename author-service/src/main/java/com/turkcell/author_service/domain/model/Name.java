package com.turkcell.author_service.domain.model;

public record Name(String value) {
    public Name{
        if(value == null || value.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if(value.length() >= 255)
            throw new IllegalArgumentException("Name length must be less than 255 characters");
    }
}
