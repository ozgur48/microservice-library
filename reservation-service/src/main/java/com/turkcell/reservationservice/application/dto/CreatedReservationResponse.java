package com.turkcell.reservationservice.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreatedReservationResponse(
        UUID reservationId,
        UUID bookId,
        UUID memberId,

        ReservationStatusDto status,
        LocalDateTime reservedAt,
        LocalDateTime expireAt
) {
}
