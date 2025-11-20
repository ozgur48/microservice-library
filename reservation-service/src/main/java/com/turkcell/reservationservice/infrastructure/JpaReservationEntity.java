package com.turkcell.reservationservice.infrastructure;

import com.turkcell.reservationservice.domain.model.ReservationStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="reservations")
public class JpaReservationEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;

    @Column(nullable = false)
    private LocalDateTime reservedAt;

    @Column(nullable = false)
    private LocalDateTime expireAt;

    @Column(name = "member_id", nullable = false)
    private UUID memberId;

    @Column(name = "book_id", nullable = false)
    private UUID bookId;

    public JpaReservationEntity(){}

    public JpaReservationEntity(UUID id, ReservationStatus status, LocalDateTime reservedAt, LocalDateTime expireAt, UUID memberId, UUID bookId) {
        this.id = id;
        this.status = status;
        this.reservedAt = reservedAt;
        this.expireAt = expireAt;
        this.memberId = memberId;
        this.bookId = bookId;
    }

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ReservationStatus status() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public LocalDateTime reservedAt() {
        return reservedAt;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }

    public LocalDateTime expireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public UUID memberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    public UUID bookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }
}
