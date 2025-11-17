package com.turkcell.loanservice.application.command;

import com.turkcell.loanservice.application.dto.CreatedLoanResponse;
import com.turkcell.loanservice.application.mapper.CreateLoanMapper;
import com.turkcell.loanservice.cqrs.Command;
import com.turkcell.loanservice.cqrs.CommandHandler;
import com.turkcell.loanservice.domain.model.*;
import com.turkcell.loanservice.domain.port.LoanRepository;
import com.turkcell.loanservice.infrastructure.messaging.Outbox.OutboxEventSaver;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CreateLoanCommandHandler implements CommandHandler<CreateLoanCommand, CreatedLoanResponse> {
    private final LoanRepository loanRepository;
    private final CreateLoanMapper createLoanMapper;
    private final OutboxEventSaver outboxEventSaver;


    public CreateLoanCommandHandler(LoanRepository loanRepository, CreateLoanMapper createLoanMapper, OutboxEventSaver outboxEventSaver) {
        this.loanRepository = loanRepository;
        this.createLoanMapper = createLoanMapper;
        this.outboxEventSaver = outboxEventSaver;
    }

    /**
     * Kitap ödünç verme komutunu işler.
     * Bu metot, Loan kaydı ve Outbox kaydını tek bir veritabanı transaction'ında tutar.
     */
    @Transactional
    @Override
    public CreatedLoanResponse handle(CreateLoanCommand command) {
        // 1. Command'dan Domain Value Object'lerine Dönüşüm
        // (Eski toDomain metodu içeriği buraya taşınır)
        // Örn: Teslim tarihi, ödünç verme tarihinden önce olamaz.
        MemberId memberId = new MemberId(command.memberId());
        BookId bookId = new BookId(command.bookId());
        StaffId staffId = new StaffId(command.staffId());
        DueDate dueDate = new DueDate(command.dueDate());
        LoanDate loanDate = new LoanDate(command.loanDate());

        // 2. Domain Aggregate'ini Oluşturma (Bu çağrı Domain kurallarını tetikler!)
        // Eğer kural ihlali olursa (DueDateMustBeAfterLoanDateException),
        // exception fırlatılır ve işlem 3. adıma geçmez.

        Loan loan = Loan.create(
                memberId,
                bookId,
                staffId,
                dueDate,
                loanDate
        );
        // 3. Aggregate'i Kaydetme (Atomik Transaction'ın İlk Parçası)
        loanRepository.save(loan);

        // 4. Domain Event'leri Outbox Tablosuna Kaydetme (Atomik Transaction'ın İkinci Parçası)
        outboxEventSaver.saveEvents(loan);

        return createLoanMapper.toResponse(loan);

    }
}
