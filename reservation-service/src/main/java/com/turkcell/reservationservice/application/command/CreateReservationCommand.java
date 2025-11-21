package com.turkcell.reservationservice.application.command;

import com.turkcell.reservationservice.application.dto.CreatedReservationResponse;
import com.turkcell.reservationservice.cqrs.Command;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateReservationCommand(
        @NotNull UUID bookId,
        @NotNull UUID memberId,
        @NotNull(message = "Rezervasyon tarihi boş olamaz.")
        @FutureOrPresent(message = "Rezervasyon başlangıç tarihi şimdiki veya gelecekteki bir zaman olmalıdır.")
        LocalDateTime reservedAt,

        @NotNull(message = "Son geçerlilik tarihi boş olamaz.")
        @FutureOrPresent(message = "Rezervasyon bitiş tarihi gelecekteki bir zaman olmalıdır.")
        LocalDateTime expireAt
        ) implements Command<CreatedReservationResponse> {
}
