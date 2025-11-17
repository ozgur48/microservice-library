package com.turkcell.loanservice.infrastructure;

import com.turkcell.loanservice.domain.model.LoanStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name ="loans")
public class JpaLoanEntity {
    @Id
    @Column(name = "id")
    private UUID id;

    // 2. ID Referansları (Foreign Keys)
    // Domain'deki MemberId, BookId ve StaffId Value Object'leri,
    // Altyapı Katmanında basit UUID'ler olarak saklanır.
    @Column(name = "member_id", nullable = false)
    private UUID memberId;

    @Column(name = "book_id", nullable = false)
    private UUID bookId;

    @Column(name = "staff_id", nullable = false)
    private UUID staffId;

    // 3. Tarih Value Object'leri (LocalDateTime'a eşleme)
    // DueDate, LoanDate ve ReturnDate Value Object'leri,
    // Veritabanında LocalDateTime olarak saklanır.
    @Column(nullable = false)
    private LocalDateTime dueDate;

    @Column(nullable = false)
    private LocalDateTime loanDate;

    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    public JpaLoanEntity() {}

    // Domain Mapper tarafından kullanılacak yapıcı
    public JpaLoanEntity(UUID id, UUID memberId, UUID bookId, UUID staffId,
                         LocalDateTime dueDate, LocalDateTime loanDate,
                         LocalDateTime returnDate, LoanStatus status) {
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.staffId = staffId;
        this.dueDate = dueDate;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public UUID staffId() {
        return staffId;
    }

    public void setStaffId(UUID staffId) {
        this.staffId = staffId;
    }

    public LocalDateTime dueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime loanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDateTime loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDateTime returnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public LoanStatus status() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }
}
