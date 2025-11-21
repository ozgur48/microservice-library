package com.turkcell.reservationservice.application.mapper;

import com.turkcell.reservationservice.application.dto.CreatedReservationResponse;
import com.turkcell.reservationservice.application.dto.ReservationStatusDto;
import com.turkcell.reservationservice.domain.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class CreateReservationMapper {
    public CreatedReservationResponse toResponse(Reservation reservation){
        var status = ReservationStatusDto.valueOf(reservation.reservationStatus().name());
        return new CreatedReservationResponse(
                reservation.reservationId().value(),
                reservation.bookId().value(),
                reservation.memberId().value(),
                status,
                reservation.reservedAt().value(),
                reservation.expireAt().value()
        );
    }
}
