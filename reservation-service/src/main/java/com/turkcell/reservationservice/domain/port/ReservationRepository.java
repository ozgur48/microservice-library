package com.turkcell.reservationservice.domain.port;

import com.turkcell.reservationservice.domain.model.Reservation;
import com.turkcell.reservationservice.domain.model.ReservationId;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findById(ReservationId reservationId);
    List<Reservation> findAll();
    List<Reservation> findAllPaged(Integer pageIndex, Integer pageSize);
    void delete(ReservationId reservationId);
}
