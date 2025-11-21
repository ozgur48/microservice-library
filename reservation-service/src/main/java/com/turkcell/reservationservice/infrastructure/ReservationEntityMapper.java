package com.turkcell.reservationservice.infrastructure;

import com.turkcell.reservationservice.domain.model.*;
import org.springframework.stereotype.Component;

@Component
public class ReservationEntityMapper {
    public JpaReservationEntity toEntity(Reservation reservation){
        return new JpaReservationEntity(
                reservation.reservationId().value(),
                reservation.reservationStatus(),
                reservation.reservedAt().value(),
                reservation.expireAt().value(),
                reservation.memberId().value(),
                reservation.bookId().value()
        );
    }
    public Reservation toDomain(JpaReservationEntity entity){
        return Reservation.rehdyrate(
                new ReservationId(entity.id()),
                new BookId(entity.bookId()),
                new MemberId(entity.memberId()),
                entity.status(),
                new ReservedAt(entity.reservedAt()),
                new ExpireAt(entity.expireAt())
        );
    }
}
