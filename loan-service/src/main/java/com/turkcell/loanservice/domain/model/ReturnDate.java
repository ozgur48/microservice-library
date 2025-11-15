package com.turkcell.loanservice.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ReturnDate(LocalDateTime value) {
    public ReturnDate{
        if(value == null)
            throw new IllegalArgumentException("Tarih null olamaz");
    }
}
