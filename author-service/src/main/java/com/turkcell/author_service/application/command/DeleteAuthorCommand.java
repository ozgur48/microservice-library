package com.turkcell.author_service.application.command;

import com.turkcell.author_service.application.dto.DeletedAuthorResponse;
import com.turkcell.author_service.core.cqrs.Command;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DeleteAuthorCommand(@NotNull UUID id) implements Command<DeletedAuthorResponse> {
}
