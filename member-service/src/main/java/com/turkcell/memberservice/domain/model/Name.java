package com.turkcell.memberservice.domain.model;

public record Name(String value) {
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 50;

    public Name{
        if(value == null || value.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if(value.length() < MIN_LENGTH || value.length() > MAX_LENGTH)
            throw new IllegalArgumentException(
                    String.format("Member name must be between %d and %d characters.",
                            MIN_LENGTH, MAX_LENGTH));
    }
}
