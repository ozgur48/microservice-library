package com.turkcell.book_service.domain.model;

import java.util.Objects;

public record AvailableCopies(Integer copy) {
        public AvailableCopies{
            Objects.requireNonNull(copy, "Available copy null olamaz");
            if(copy < 0)
                throw new IllegalArgumentException("Avaliable copy negatif olamaz");

    }
}
