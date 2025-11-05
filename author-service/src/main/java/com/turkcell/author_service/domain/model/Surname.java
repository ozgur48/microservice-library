package com.turkcell.author_service.domain.model;

public record Surname(String value) {
    public Surname{
        if(value == null || value.isEmpty())
            throw new IllegalArgumentException("Surname cannot be null or empty");
        if(value.length() >= 255)
            throw new IllegalArgumentException("Surname length must be less than 255 characters");
    }
}
