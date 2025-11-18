package com.turkcell.staff_service.domain.exception;

import com.turkcell.staff_service.domain.model.StaffId;

import java.util.UUID;

public class StaffNotFoundException extends RuntimeException {
    public StaffNotFoundException(UUID id) {
        super("Staff with id: " + id + " could not find!");
    }

    public StaffNotFoundException(StaffId id) {
        super("Staff with id: " + id + " could not find!");
    }

}
