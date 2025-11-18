package com.turkcell.staff_service.application.command;

import com.turkcell.staff_service.application.dto.CreatedStaffResponse;
import com.turkcell.staff_service.cqrs.Command;
import com.turkcell.staff_service.domain.model.Email;
import com.turkcell.staff_service.domain.model.Phone;
import com.turkcell.staff_service.domain.model.StaffLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateStaffCommand(@NotBlank String name,
                                 @NotNull StaffLevel staffLevel,
                                 @NotNull Email email,
                                 @NotNull Phone phone) implements Command<CreatedStaffResponse> {
}
