package com.turkcell.fineservice.infrastructure.messaging;

import com.turkcell.fineservice.domain.model.FineStatus;
import com.turkcell.fineservice.domain.model.FineType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "fines")
public class JpaFineEntity {
    // 1. Kök Varlık ID'si (FineId VO'suna karşılık gelir)
    @Id
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID id;

    // 2. Aggregate Referansları (LoanId, MemberId, StaffId VO'larına karşılık gelir)
    @Column(name = "loan_id", nullable = false, columnDefinition = "uuid")
    private UUID loanId;

    @Column(name = "member_id", nullable = false, columnDefinition = "uuid")
    private UUID memberId;

    @Column(name = "staff_id", nullable = false, columnDefinition = "uuid")
    private UUID staffId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FineStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FineType fineType;

    @Column(nullable = false)
    private LocalDateTime appliedAt;

    @Column(nullable = false, length = 512) // Uzunluk kısıtlaması
    private String reason;

    public JpaFineEntity(UUID id, UUID loanId, UUID memberId, UUID staffId,
                         BigDecimal amount, FineStatus status, FineType fineType,
                         LocalDateTime appliedAt, String reason) {
        this.id = id;
        this.loanId = loanId;
        this.memberId = memberId;
        this.staffId = staffId;
        this.amount = amount;
        this.status = status;
        this.fineType = fineType;
        this.appliedAt = appliedAt;
        this.reason = reason;
    }
    public JpaFineEntity(){}

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID loanId() {
        return loanId;
    }

    public void setLoanId(UUID loanId) {
        this.loanId = loanId;
    }

    public UUID memberId() {
        return memberId;
    }

    public void setMemberId(UUID memberId) {
        this.memberId = memberId;
    }

    public UUID staffId() {
        return staffId;
    }

    public void setStaffId(UUID staffId) {
        this.staffId = staffId;
    }

    public BigDecimal amount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public FineStatus status() {
        return status;
    }

    public void setStatus(FineStatus status) {
        this.status = status;
    }

    public FineType fineType() {
        return fineType;
    }

    public void setFineType(FineType fineType) {
        this.fineType = fineType;
    }

    public LocalDateTime appliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(LocalDateTime appliedAt) {
        this.appliedAt = appliedAt;
    }

    public String reason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
