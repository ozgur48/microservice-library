package com.turkcell.loanservice.domain.model;

public enum LoanStatus {
    ACTIVE,     // Kitap hâlâ üyede
    RETURNED,   // Kitap iade edildi
    CANCELED,
    OVERDUE,
    // Süresi geçti, hâlâ iade edilmedi
}