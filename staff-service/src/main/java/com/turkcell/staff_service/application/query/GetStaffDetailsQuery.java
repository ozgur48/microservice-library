package com.turkcell.staff_service.application.query;

import com.turkcell.staff_service.application.dto.StaffDetails;
import com.turkcell.staff_service.cqrs.Query;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GetStaffDetailsQuery(@NotNull UUID id) implements Query<StaffDetails> {
}
