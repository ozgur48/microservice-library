package com.turkcell.staff_service.application.dto;

import com.turkcell.staff_service.domain.model.Email;
import com.turkcell.staff_service.domain.model.Phone;
import jakarta.validation.constraints.NotBlank;

public record CreateStaffCommand(@NotBlank String name,
                                 @NotBlank Email email,
                                 @NotBlank Phone phone) implements Command<CreateStaffResponse>{
}
