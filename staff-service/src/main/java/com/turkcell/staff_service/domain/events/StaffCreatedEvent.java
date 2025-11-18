package com.turkcell.staff_service.domain.events;

import com.turkcell.staff_service.domain.model.StaffId;

import java.util.UUID;

public record StaffCreatedEvent(StaffId staffId) {
    public StaffCreatedEvent{
        if (staffId == null){
            throw new IllegalArgumentException("staffId cannot be null!");
        }
    }

}
