package com.turkcell.loanservice.domain.model;

import java.util.Objects;
import java.util.UUID;

public record BookId(UUID value) {
    public BookId{
        Objects.requireNonNull(value, "BookId can not be null.");
    }
}
