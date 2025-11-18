package com.turkcell.staff_service.domain.events;

import java.util.UUID;

public record StaffCreatedFailedEvent(UUID staffId) {
    public StaffCreatedFailedEvent{
        if (staffId == null){
            throw new IllegalArgumentException("staffId cannot be null!");
        }
    }
}
