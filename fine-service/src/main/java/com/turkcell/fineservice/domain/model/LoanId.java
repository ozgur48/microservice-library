package com.turkcell.fineservice.domain.model;

import java.util.Objects;
import java.util.UUID;

public record LoanId(UUID value) {
    public LoanId{
        Objects.requireNonNull(value, "Value for loanId cannot be null");
    }
}

