package com.turkcell.author_service.application.command;

import com.turkcell.author_service.application.dto.CreatedAuthorResponse;
import com.turkcell.author_service.core.cqrs.Command;
import jakarta.validation.constraints.NotBlank;


public record CreateAuthorCommand(@NotBlank String name, @NotBlank String surname)
        implements Command<CreatedAuthorResponse> {}
