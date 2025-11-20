package com.turkcell.reservationservice.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaReservationRepository extends JpaRepository<JpaReservationEntity, UUID> {
}
