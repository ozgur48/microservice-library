package com.turkcell.memberservice.application.command;

import com.turkcell.memberservice.application.dto.UpdatedMemberResponse;
import com.turkcell.memberservice.core.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateMemberCommand(@NotNull UUID id,
                                  @NotBlank String name,
                                  @NotBlank String email,
                                  @NotBlank String phone,
                                  @NotBlank String address)implements Command<UpdatedMemberResponse> {
}
