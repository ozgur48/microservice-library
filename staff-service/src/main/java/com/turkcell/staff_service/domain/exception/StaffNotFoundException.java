package com.turkcell.staff_service.domain.exception;

public class StaffNotFoundException extends RuntimeException {
  public StaffNotFoundException(String message) {
    super(message);
  }
}
