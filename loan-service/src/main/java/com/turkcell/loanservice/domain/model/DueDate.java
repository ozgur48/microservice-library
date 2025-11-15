package com.turkcell.loanservice.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DueDate(LocalDateTime value) {
    public DueDate{
        if(value == null)
            throw new IllegalArgumentException("Tarih null olamaz");
    }
}