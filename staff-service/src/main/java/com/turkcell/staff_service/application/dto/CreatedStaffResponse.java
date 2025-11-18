package com.turkcell.staff_service.application.dto;

import java.util.UUID;

public record CreatedStaffResponse(UUID id,
                                  String name) {
}
