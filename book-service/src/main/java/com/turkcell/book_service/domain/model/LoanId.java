package com.turkcell.book_service.domain.model;

import java.util.Objects;
import java.util.UUID;

public record LoanId(UUID value) {
    public LoanId{
        Objects.requireNonNull(value, "LoanId cannot be null");
    }
}
