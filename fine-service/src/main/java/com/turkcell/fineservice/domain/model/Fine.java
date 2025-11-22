package com.turkcell.fineservice.domain.model;

import com.turkcell.fineservice.domain.event.DomainEvent;
import com.turkcell.fineservice.domain.event.FineCancelledEvent;
import com.turkcell.fineservice.domain.event.FineCreatedEvent;
import com.turkcell.fineservice.domain.exception.AmountCannotBeNegative;
import com.turkcell.fineservice.domain.exception.BusinessException;

import java.math.BigDecimal;
import java.util.*;

public class Fine implements AggregateRoot{
    private FineId fineId;

    private LoanId loanId;
    private MemberId memberId;
    private StaffId staffId;

    private Amount amount;
    private Reason reason;
    private FineStatus fineStatus;
    private FineType fineType;
    private AppliedAt appliedAt;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    public Fine(FineId fineId, LoanId loanId, MemberId memberId, StaffId staffId, FineStatus fineStatus, Amount amount, Reason reason, FineType fineType, AppliedAt appliedAt) {
        this.fineId = fineId;
        this.loanId = loanId;
        this.memberId = memberId;
        this.staffId = staffId;
        this.fineStatus = fineStatus;
        this.amount = amount;
        this.reason = reason;
        this.fineType = fineType;
        this.appliedAt = appliedAt;
    }

    public Fine(){}

    public static Fine create(LoanId loanId, MemberId memberId, Amount amount, StaffId staffId,
                              Reason reason, FineType fineType ){
        FineId fineId = FineId.generate();
        AppliedAt appliedAt = AppliedAt.now();

        // domain invariant kontrolü
        if(amount.value().compareTo(BigDecimal.ZERO) == 0){
            throw new AmountCannotBeNegative(amount.value());
        }
        // fine aggreagate yarat
        Fine fine = new Fine(
                fineId,
                loanId,
                memberId,
                staffId,
                FineStatus.PENDING,
                amount,
                reason,
                fineType,
                appliedAt
        );
        // fine event yarat
        fine.domainEvents.add(
                new FineCreatedEvent(
                        fine.fineId,
                        fine.staffId,
                        fine.memberId,
                        fine.loanId,
                        fine.amount
                )
        );
        return fine;
    }
    public static Fine rehydrate(FineId fineId, LoanId loanId, MemberId memberId,
                                 StaffId staffId, FineStatus fineStatus, Amount amount,
                                 Reason reason, FineType fineType, AppliedAt appliedAt){
        return new Fine(fineId, loanId ,memberId, staffId, fineStatus ,amount, reason, fineType, appliedAt);
    }
    // ceza iptal davranışı
    public void cancelFine(Reason reason, StaffId staffId){
        // 1. Domain invariant kurali
        if(this.fineStatus != FineStatus.PENDING){
            // ödenmiş ve iptal edilmiş ceza tekrar iptal edilemez
            throw new BusinessException(String.format(
                    "Fine (ID: %s) %s status cancelled so can not cancel again.", this.fineId, this.fineStatus
            ));
        }

        // ceza durumunu  cancel yap ve borcu 0'la
        this.fineStatus = FineStatus.CANCELLED;
        this.amount = new Amount(BigDecimal.ZERO);

        // domain event kaydı
        this.domainEvents.add(
                new FineCancelledEvent(
                        this.fineId,
                        staffId,
                        this.memberId,
                        reason
                )
        );
    }



    public FineId fineId() {
        return fineId;
    }

    public LoanId loanId() {
        return loanId;
    }

    public MemberId memberId() {
        return memberId;
    }

    public StaffId staffId() {
        return staffId;
    }
    public Amount amount() {
        return amount;
    }

    public Reason reason() {
        return reason;
    }

    public FineStatus fineStatus() {
        return fineStatus;
    }

    public FineType fineType() {
        return fineType;
    }

    public AppliedAt appliedAt() {
        return appliedAt;
    }

    public List<DomainEvent> domainEvents() {
        return domainEvents;
    }

    @Override
    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    @Override
    public void clearDomainEvents() {
        domainEvents.clear();
    }

    @Override
    public UUID getIdValue() {
        return this.fineId.value();
    }
}
