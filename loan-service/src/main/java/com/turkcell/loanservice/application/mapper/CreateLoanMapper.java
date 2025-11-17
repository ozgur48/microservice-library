package com.turkcell.loanservice.application.mapper;

import com.turkcell.loanservice.application.command.CreateLoanCommand;
import com.turkcell.loanservice.application.dto.CreatedLoanResponse;
import com.turkcell.loanservice.application.dto.LoanStatusDto;
import com.turkcell.loanservice.domain.model.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CreateLoanMapper {
    public CreatedLoanResponse toResponse(Loan loan){
        var status = LoanStatusDto.valueOf(loan.loanStatus().name());

        LocalDateTime safeReturnDate = loan.returnDate() != null
                                     ? loan.returnDate().value()
                                     : null;

        return new CreatedLoanResponse(
                loan.loanId().value(),
                loan.memberId().value(),
                loan.bookId().value(),
                loan.staffId().value(),
                loan.dueDate().value(),
                loan.loanDate().value(),
                safeReturnDate,
                status
        );
    }
}
