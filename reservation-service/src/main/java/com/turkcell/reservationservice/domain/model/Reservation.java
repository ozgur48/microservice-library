package com.turkcell.reservationservice.domain.model;

import com.turkcell.reservationservice.domain.event.DomainEvent;
import com.turkcell.reservationservice.domain.event.ReservationCancelledEvent;
import com.turkcell.reservationservice.domain.event.ReservationCreatedEvent;
import com.turkcell.reservationservice.domain.exception.ReservationCannotBeCancelledException;
import com.turkcell.reservationservice.domain.exception.ReservationExpireAtCanNotBeBeforeReservedAtException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Reservation implements AggregateRoot {
    private final ReservationId reservationId;

    private final BookId bookId;
    private final MemberId memberId;

    private ReservationStatus reservationStatus;
    private ReservedAt reservedAt;
    private ExpireAt expireAt;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Reservation(ReservationId reservationId, BookId bookId,
                       MemberId memberId, ReservationStatus reservationStatus,
                       ReservedAt reservedAt, ExpireAt expireAt) {
        this.reservationId = reservationId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.reservationStatus = reservationStatus;
        this.reservedAt = reservedAt;
        this.expireAt = expireAt;
    }

    public static Reservation createReservation(BookId bookId, MemberId memberId,
                                  ReservedAt reservedAt, ExpireAt expireAt){
        // 1 reservationId yarat
        ReservationId newReservationId = ReservationId.generate();

        //2. domain invariant control
        if(expireAt.value().isBefore(reservedAt.value())){
            throw new ReservationExpireAtCanNotBeBeforeReservedAtException(newReservationId.value(),
                    expireAt.value(), reservedAt.value());
        }

        // 3. reservation aggregate created
        Reservation reservation = new Reservation(
                newReservationId,
                bookId,
                memberId,
                ReservationStatus.APPROVED,
                reservedAt,
                expireAt
        );

        // 4. domain event kaydı
        reservation.domainEvents.add(
                new ReservationCreatedEvent(
                        newReservationId,
                        bookId,
                        memberId,
                        reservedAt.value(),
                        expireAt.value()
                )
        );
        return reservation;
    }

    public static Reservation rehdyrate(ReservationId reservationId, BookId bookId, MemberId memberId,
                                        ReservationStatus reservationStatus, ReservedAt reservedAt, ExpireAt expireAt){
        return new Reservation(
                reservationId,
                bookId,
                memberId,
                reservationStatus,
                reservedAt,
                expireAt
        );
    }

    public void cancelReservation(String reason){
        if(this.reservationStatus != ReservationStatus.PENDING && this.reservationStatus != ReservationStatus.APPROVED ) {
            throw new ReservationCannotBeCancelledException(this.reservationId.value(), reservationStatus);
        }
        this.reservationStatus = ReservationStatus.CANCELED;
        this.domainEvents.add(
                new ReservationCancelledEvent(this.reservationId, reason)
        );
    }

    public ReservationId reservationId() {
        return reservationId;
    }

    public BookId bookId() {
        return bookId;
    }

    public MemberId memberId() {
        return memberId;
    }

    public ReservationStatus reservationStatus() {
        return reservationStatus;
    }

    public ReservedAt reservedAt() {
        return reservedAt;
    }

    public ExpireAt expireAt() {
        return expireAt;
    }

    @Override
    // olayları okumak için
    public List<DomainEvent> getDomainEvents(){
        return Collections.unmodifiableList(domainEvents);
    }
    @Override
    public void clearDomainEvents(){
        this.domainEvents.clear();
    }

    @Override
    public UUID getIdValue() {
        return this.reservationId().value();
    }
}
