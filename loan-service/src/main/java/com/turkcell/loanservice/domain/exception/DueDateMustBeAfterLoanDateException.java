package com.turkcell.loanservice.domain.exception;

public class DueDateMustBeAfterLoanDateException extends RuntimeException {
  public DueDateMustBeAfterLoanDateException(String message) {
    super(message);
  }
}
