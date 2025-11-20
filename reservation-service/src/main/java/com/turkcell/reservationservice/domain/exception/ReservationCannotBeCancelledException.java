package com.turkcell.reservationservice.domain.exception;

import com.turkcell.reservationservice.domain.model.ReservationStatus;

import java.util.UUID;

public class ReservationCannotBeCancelledException extends IllegalStateException {
    public ReservationCannotBeCancelledException(UUID reservationId, ReservationStatus currentReservationStatus) {
        super(String.format(
                "Reservation (ID: %s) cannot be cancelled because it is currently in %s status.",
                reservationId.toString(),
                currentReservationStatus.name()
        ));
    }
}
