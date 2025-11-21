package com.turkcell.reservationservice.application.command;

import com.turkcell.reservationservice.application.dto.CreatedReservationResponse;
import com.turkcell.reservationservice.application.mapper.CreateReservationMapper;
import com.turkcell.reservationservice.cqrs.CommandHandler;
import com.turkcell.reservationservice.domain.model.*;
import com.turkcell.reservationservice.domain.port.ReservationRepository;
import com.turkcell.reservationservice.infrastructure.messaging.Outbox.OutboxEventSaver;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateReservationCommandHandler implements CommandHandler<CreateReservationCommand, CreatedReservationResponse> {
    private final CreateReservationMapper mapper;
    private final ReservationRepository reservationRepository;
    private final OutboxEventSaver outboxEventSaver;

    public CreateReservationCommandHandler(CreateReservationMapper mapper, ReservationRepository reservationRepository, OutboxEventSaver outboxEventSaver) {
        this.mapper = mapper;
        this.reservationRepository = reservationRepository;
        this.outboxEventSaver = outboxEventSaver;
    }

    @Transactional
    @Override
    public CreatedReservationResponse handle(CreateReservationCommand command) {
        // 1. Command'dan Domain Value Object'lerine Dönüşüm
        // (Eski toDomain metodu içeriği buraya taşınır)
        BookId bookId = new BookId(command.bookId());
        MemberId memberId = new MemberId(command.memberId());
        ReservedAt reservedAt = new ReservedAt(command.reservedAt());
        ExpireAt expireAt = new ExpireAt(command.reservedAt());

        // 2. Domain Aggregate'ini Oluşturma (Bu çağrı Domain kurallarını tetikler!)
        // Eğer kural ihlali olursa
        // exception fırlatılır ve işlem 3. adıma geçmez.

        Reservation reservation = Reservation.createReservation(
                bookId,
                memberId,
                reservedAt,
                expireAt
        );

        // 3. Aggregate'i kaydetme, transaction'ın İlk Parçası
        reservationRepository.save(reservation);

        // 4. domain eventleri outbox tablosuna kaydetma,  transaction'ın ikinci Parçası
        outboxEventSaver.saveEvents(reservation);
        return mapper.toResponse(reservation);
    }
}
