package com.turkcell.fineservice.application.command;

import com.turkcell.fineservice.application.dto.CreatedFineResponse;
import com.turkcell.fineservice.application.mapper.CreateFineMapper;
import com.turkcell.fineservice.cqrs.CommandHandler;
import com.turkcell.fineservice.domain.model.*;
import com.turkcell.fineservice.domain.port.FineRepository;
import com.turkcell.fineservice.infrastructure.messaging.outbox.OutboxEventSaver;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateFineCommandHandler implements CommandHandler<CreateFineCommand, CreatedFineResponse> {
    private final FineRepository fineRepository;
    private final CreateFineMapper fineMapper;
    private final OutboxEventSaver outboxEventSaver;

    public CreateFineCommandHandler(FineRepository fineRepository,
                                    CreateFineMapper fineMapper,
                                    OutboxEventSaver outboxEventSaver) {
        this.fineRepository = fineRepository;
        this.fineMapper = fineMapper;
        this.outboxEventSaver = outboxEventSaver;
    }

    @Override
    @Transactional
    public CreatedFineResponse handle(CreateFineCommand command) {
        // command'dan domaine vo dönüşüm
        LoanId loanId = new LoanId(command.loanId());
        MemberId memberId = new MemberId(command.memberId());
        StaffId staffId = new StaffId(command.staffId());
        Amount amount = new Amount(command.amount());
        Reason reason = new Reason(command.reason());
        FineType fineType = FineType.valueOf(command.fineTypeDto().name());

        Fine fine = Fine.create(
                loanId,
                memberId,
                staffId,
                amount,
                reason,
                fineType
        );
        // aggregate'i kaydet
        fineRepository.save(fine);

        // domaineventleri outbox'a kaydetma
        outboxEventSaver.saveEvents(fine);

        return fineMapper.toResponse(fine);
    }
}
