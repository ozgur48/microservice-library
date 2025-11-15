package com.turkcell.memberservice.application.command;

import com.turkcell.memberservice.application.dto.DeletedMemberResponse;
import com.turkcell.memberservice.core.cqrs.Command;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DeleteMemberCommand(@NotNull UUID id) implements Command<DeletedMemberResponse> {
}
