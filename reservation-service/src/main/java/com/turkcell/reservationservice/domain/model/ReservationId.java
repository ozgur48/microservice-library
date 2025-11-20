package com.turkcell.reservationservice.domain.model;

import java.util.Objects;
import java.util.UUID;

public record ReservationId(UUID value) {
    public ReservationId{
        Objects.requireNonNull(value, "ReservationId can not be null");
    }
    public static ReservationId generate(){
        return new ReservationId(UUID.randomUUID());
    }
}
