package com.turkcell.loanservice.infrastructure;

import com.turkcell.loanservice.domain.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LoanEntityMapper {
    public JpaLoanEntity toEntity(Loan loan){
        LocalDateTime safeReturnDate = loan.returnDate() != null
                ? loan.returnDate().value() // Eğer değilse value() çağır
                : null; // Eğer null ise null kal

        return new JpaLoanEntity(
                loan.loanId().value(),
                loan.memberId().value(),
                loan.bookId().value(),
                loan.staffId().value(),
                loan.dueDate().value(),
                loan.loanDate().value(),
                safeReturnDate,
                loan.loanStatus()
        );
    }
    public Loan toDomain(JpaLoanEntity entity){
        ReturnDate returnDate = null;
        if(entity.returnDate() != null){
            returnDate = new ReturnDate(entity.returnDate());
        }
        return Loan.rehdyrate(
                new LoanId(entity.id()),
                new MemberId(entity.memberId()),
                new BookId(entity.bookId()),
                new StaffId(entity.staffId()),
                new DueDate(entity.dueDate()),
                new LoanDate(entity.loanDate()),
                returnDate,
                entity.status()
        );
    }
}
