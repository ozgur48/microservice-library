package com.turkcell.loanservice.domain.model;

import com.turkcell.loanservice.domain.exception.DueDateMustBeAfterCurrentDueDateException;
import com.turkcell.loanservice.domain.exception.LoanAlreadyReturnedException;
import com.turkcell.loanservice.domain.exception.ReturnDateMustBeAfterLoanDate;

import java.time.LocalDateTime;

public class Loan {

    private final LoanId loanId;
    private final MemberId memberId;
    private final BookId bookId;
    private final StaffId staffId;

    private DueDate dueDate;
    private LoanDate loanDate;
    private ReturnDate returnDate;
    private LoanStatus loanStatus;

    public Loan(LoanId loanId, MemberId memberId, BookId bookId, StaffId staffId,
                DueDate dueDate, LoanDate loanDate, ReturnDate returnDate, LoanStatus loanStatus) {
        this.loanId = loanId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.staffId = staffId;
        this.dueDate = dueDate;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.loanStatus = loanStatus;
    }

    public static Loan create(MemberId memberId, BookId bookId, StaffId staffId,
                              DueDate dueDate, LoanDate loanDate, ReturnDate returnDate, LoanStatus loanStatus){
        return new Loan(
                LoanId.generate(), memberId,
                bookId, staffId, dueDate, loanDate,
                returnDate, loanStatus
        );
    }
    public static Loan rehdyrate(LoanId loanId, MemberId memberId, BookId bookId, StaffId staffId,
                                 DueDate dueDate, LoanDate loanDate, ReturnDate returnDate, LoanStatus loanStatus){
        return new Loan(loanId, memberId,
                bookId, staffId, dueDate, loanDate,
                returnDate, loanStatus
        );
    }
    public void extendDueDate(DueDate newDueDate){
        // ödünç durumunu kontrol et, teslim edildeyse süre uzatılmaz!
        if(this.loanStatus == LoanStatus.RETURNED){
            throw new LoanAlreadyReturnedException(this.loanId.value());
        }
        // Kitabı ödünç süresini erteleme şuandan önce olamaz
        if(newDueDate.value().isBefore(this.dueDate.value())){
            throw new DueDateMustBeAfterCurrentDueDateException(this.loanId.value(), this.dueDate.value(), newDueDate.value());
        }
    }

    public void markAsReturned(ReturnDate actualReturnDate){
        // iade edilen tekrar iade edilemez.
        if(this.loanStatus == LoanStatus.RETURNED){
            throw new LoanAlreadyReturnedException(this.loanId.value());
        }
        // iade tarihi, ödünç tarihinden önce olamaz
        if(actualReturnDate.value().isBefore(this.loanDate.value())){
            throw new ReturnDateMustBeAfterLoanDate(this.loanId.value(), actualReturnDate.value(), this.loanDate.value());
        }
        this.returnDate = actualReturnDate;
        this.loanStatus = LoanStatus.RETURNED;

        // 4. Domain Event Yayınlama (Nihai tutarlılık için)
        // Bu olay, başka bir Aggregate'i (Book Aggregate'i, Member Aggregate'i) bilgilendirmelidir.
        // Örneğin, Book Aggregate'i müsait duruma geçmeli.
        // DomainEvents.publish(new BookReturnedEvent(this.bookId, this.memberId, this.loanId));
    }

    public void markAsOverDue(LocalDateTime systemCurrentTime){
        // iade edilmiş ve iptal edilen gecikmeye girmez
        if(this.loanStatus == LoanStatus.RETURNED || this.loanStatus == LoanStatus.CANCELED){
            return;
        }
        // şuan ki zaman, teslim tarihinden sonra olmalı
        if(systemCurrentTime.isAfter(this.dueDate.value()) && this.loanStatus != LoanStatus.OVERDUE){
            this.loanStatus = LoanStatus.OVERDUE;

            // 4. Domain Event Yayınlama
            // Bu olay, ceza (fee) hesaplama servisini veya Member Aggregate'i bilgilendirmelidir.
            // DomainEvents.publish(new LoanBecameOverdueEvent(this.loanId, this.memberId, this.bookId));
        }
    }

    public LoanId loanId() {
        return loanId;
    }

    public DueDate dueDate() {
        return dueDate;
    }

    public LoanDate loanDate() {
        return loanDate;
    }

    public ReturnDate returnDate() {
        return returnDate;
    }

    public LoanStatus loanStatus() {
        return loanStatus;
    }

    public MemberId memberId() {
        return memberId;
    }

    public BookId bookId() {
        return bookId;
    }

    public StaffId staffId() {
        return staffId;
    }
}
