package com.turkcell.memberservice.application.command;

import com.turkcell.memberservice.application.dto.CreatedMemberResponse;
import com.turkcell.memberservice.application.dto.MemberShipLevelDto;
import com.turkcell.memberservice.application.dto.MemberStatusDto;
import com.turkcell.memberservice.core.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CreateMemberCommand(@NotBlank String name,
                                  @NotBlank String email,
                                  @NotBlank String phone,
                                  @NotBlank String address,
                                  @NotNull MemberShipLevelDto memberShipLevel,
                                  @NotNull MemberStatusDto memberStatus) implements Command<CreatedMemberResponse> {
}
