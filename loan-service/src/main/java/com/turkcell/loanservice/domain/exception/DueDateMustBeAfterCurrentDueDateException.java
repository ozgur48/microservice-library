package com.turkcell.loanservice.domain.exception;

import java.time.LocalDateTime;
import java.util.UUID;

public class DueDateMustBeAfterCurrentDueDateException extends IllegalArgumentException{
    public DueDateMustBeAfterCurrentDueDateException(UUID loanId, LocalDateTime currentDueDate, LocalDateTime newDueDate){
        super(String.format(
                "Loan (ID: %s) due date extension failed. New due date (%s) must be after the current due date (%s).",
                loanId,
                currentDueDate,
                newDueDate
        ));

    }
}
