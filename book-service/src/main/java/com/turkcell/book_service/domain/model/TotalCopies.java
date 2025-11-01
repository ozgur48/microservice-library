package com.turkcell.book_service.domain.model;

import java.util.Objects;

public record TotalCopies(Integer copy) {
    public TotalCopies{
        Objects.requireNonNull(copy, "Total copy null olamaz");
        if(copy < 0)
            throw new IllegalArgumentException("Total copies negatif olamaz");

    }
}
