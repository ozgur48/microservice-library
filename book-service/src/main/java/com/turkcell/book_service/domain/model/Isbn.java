package com.turkcell.book_service.domain.model;


import java.util.Objects;

public record Isbn(String value) {
    public Isbn{
        String digits = value.replaceAll("-", "");
        Objects.requireNonNull(value, "ISBN boş olamaz");
        if(value.length() != 13)
            throw new IllegalArgumentException("ISBN 13 basamaklı olmalı.");
        if (!digits.matches("\\d{9}[\\dX]|\\d{13}")) {
            throw new IllegalArgumentException("ISBN contains invalid characters");
        }

    }
}
