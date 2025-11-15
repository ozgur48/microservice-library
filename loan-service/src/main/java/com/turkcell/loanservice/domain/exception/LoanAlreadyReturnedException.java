package com.turkcell.loanservice.domain.exception;

import java.util.UUID;

public class LoanAlreadyReturnedException extends IllegalStateException{
    public LoanAlreadyReturnedException(UUID loanId){
        super("Loan with ID: " + loanId + "is already returned and can not be extended");
    }
}
