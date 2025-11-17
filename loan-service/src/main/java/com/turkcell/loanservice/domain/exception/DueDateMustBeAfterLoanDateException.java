package com.turkcell.loanservice.domain.exception;

import java.time.LocalDateTime;
import java.util.UUID;

public class DueDateMustBeAfterLoanDateException extends RuntimeException {
    public DueDateMustBeAfterLoanDateException(UUID loanId, LocalDateTime loanDate, LocalDateTime dueDate) {
        super(String.format(
                "Due date validation failed for Loan (ID: %s). Due date (%s) must be after loan date (%s).",
                loanId.toString(), dueDate.toString(), loanDate.toString()
        ));
    }
}
