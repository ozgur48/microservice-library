package com.turkcell.author_service.application.command;

import com.turkcell.author_service.application.dto.UpdatedAuthorResponse;
import com.turkcell.author_service.core.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateAuthorCommand(@NotNull UUID authorId, @NotBlank String name, @NotBlank String surname) implements Command<UpdatedAuthorResponse> {
}
