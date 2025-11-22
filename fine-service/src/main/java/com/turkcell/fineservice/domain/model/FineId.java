package com.turkcell.fineservice.domain.model;

import java.util.Objects;
import java.util.UUID;

public record FineId(UUID value) {
    public FineId{
        Objects.requireNonNull(value, "FineId can not be null");
    }
    public static FineId generate(){
        return new FineId(UUID.randomUUID());
    }
}
