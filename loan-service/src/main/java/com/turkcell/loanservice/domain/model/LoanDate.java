package com.turkcell.loanservice.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record LoanDate(LocalDateTime value) {
    public LoanDate{
        if(value == null)
            throw new IllegalArgumentException("Tarih null olamaz");
    }
}
