package com.turkcell.reservationservice.infrastructure;

import com.turkcell.reservationservice.domain.model.Reservation;
import com.turkcell.reservationservice.domain.model.ReservationId;
import com.turkcell.reservationservice.domain.port.ReservationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ReservationRepositoryAdapter implements ReservationRepository {
    private final ReservationEntityMapper reservationEntityMapper;
    private final JpaReservationRepository repository;

    public ReservationRepositoryAdapter(ReservationEntityMapper reservationEntityMapper, JpaReservationRepository repository) {
        this.reservationEntityMapper = reservationEntityMapper;
        this.repository = repository;
    }


    @Override
    public Reservation save(Reservation reservation) {
        JpaReservationEntity entity = reservationEntityMapper.toEntity(reservation);
        entity = repository.save(entity);
        return reservationEntityMapper.toDomain(entity);
    }

    @Override
    public Optional<Reservation> findById(ReservationId reservationId) {
        return repository.findById(reservationId.value()).map(reservationEntityMapper::toDomain);
    }

    @Override
    public List<Reservation> findAll() {
        return repository.findAll().stream().map(reservationEntityMapper::toDomain).toList();
    }

    @Override
    public List<Reservation> findAllPaged(Integer pageIndex, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        return repository.findAll(pageRequest).stream().map(reservationEntityMapper::toDomain).toList();
    }

    @Override
    public void delete(ReservationId reservationId) {
        repository.deleteById(reservationId.value());
    }
}
