package com.turkcell.book_service.domain.model;

import java.util.Objects;

public record Title(String value) {
    public Title{
        Objects.requireNonNull(value, "Title null olamaz.");
        if(value.length() > 100)
            throw new IllegalArgumentException("title 100 haneden büyük olamaz");
    }
}
