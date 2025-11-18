package com.turkcell.staff_service.application.command;

import com.turkcell.staff_service.application.dto.UpdatedStaffResponse;
import com.turkcell.staff_service.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateStaffCommand(@NotNull UUID id,
                                 @NotBlank String name,
                                @NotNull String email,
                                @NotNull String phone) implements Command<UpdatedStaffResponse> {
}
