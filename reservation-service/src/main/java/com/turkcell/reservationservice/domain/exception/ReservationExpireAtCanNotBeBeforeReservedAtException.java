package com.turkcell.reservationservice.domain.exception;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReservationExpireAtCanNotBeBeforeReservedAtException extends RuntimeException {
    public ReservationExpireAtCanNotBeBeforeReservedAtException(UUID reservationId, LocalDateTime expireAt, LocalDateTime reservedAt) {
        super(String.format(
                "Expire date cannot be before reserved date.",
                reservationId.toString(), expireAt, reservedAt
        ));
    }
}
