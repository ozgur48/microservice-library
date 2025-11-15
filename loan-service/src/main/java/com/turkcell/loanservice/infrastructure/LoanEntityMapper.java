package com.turkcell.loanservice.infrastructure;

import com.turkcell.loanservice.domain.model.*;
import org.springframework.stereotype.Component;

@Component
public class LoanEntityMapper {
    public JpaLoanEntity toEntity(Loan loan){
        return new JpaLoanEntity(
                loan.loanId().value(),
                loan.memberId().value(),
                loan.bookId().value(),
                loan.staffId().value(),
                loan.dueDate().value(),
                loan.loanDate().value(),
                loan.returnDate().value(),
                loan.loanStatus()
        );
    }
    public Loan toDomain(JpaLoanEntity entity){
        return Loan.rehdyrate(
                new LoanId(entity.id()),
                new MemberId(entity.memberId()),
                new BookId(entity.bookId()),
                new StaffId(entity.staffId()),
                new DueDate(entity.dueDate()),
                new LoanDate(entity.loanDate()),
                new ReturnDate(entity.returnDate()),
                entity.status()
        );
    }
}
