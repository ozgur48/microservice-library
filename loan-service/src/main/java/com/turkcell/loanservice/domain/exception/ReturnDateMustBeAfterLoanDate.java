package com.turkcell.loanservice.domain.exception;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReturnDateMustBeAfterLoanDate extends IllegalArgumentException{
    public ReturnDateMustBeAfterLoanDate(UUID loanId, LocalDateTime returnDate, LocalDateTime loanDate) {
        super(String.format(
                "Return date validation failed for Loan (ID: %s). The provided return date (%s) cannot be before the loan date (%s).",
                loanId.toString(),
                returnDate.toString(),
                loanDate.toString()
        ));
    }
}
