package com.turkcell.book_service.domain.model;

import java.util.Objects;

public record Language(String value) {
    public Language{
        Objects.requireNonNull(value, "Language boş olamaz");
        if(value.length() > 100)
            throw new IllegalArgumentException("Language 100 den büyük olamaz");
    }
}
