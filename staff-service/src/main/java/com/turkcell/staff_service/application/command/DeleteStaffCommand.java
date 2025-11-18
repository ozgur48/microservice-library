package com.turkcell.staff_service.application.command;

import com.turkcell.staff_service.application.dto.DeletedStaffResponse;
import com.turkcell.staff_service.cqrs.Command;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record DeleteStaffCommand(@NotBlank UUID id) implements Command<DeletedStaffResponse> {
}
