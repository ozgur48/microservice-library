package com.turkcell.publisher_service.domain.model;

import java.util.Objects;

public record Name(String value) {
    public Name{
        Objects.requireNonNull(value, "Name null olamaz");

        if(value.isBlank())
            throw new IllegalArgumentException("Name boş olamaz");
        if(value.length() < 2 || value.length() > 100)
            throw new IllegalArgumentException("Geçerli uzunlukta isim giriniz.");
    }
}