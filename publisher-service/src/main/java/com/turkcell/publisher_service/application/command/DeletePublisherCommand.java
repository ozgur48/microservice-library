package com.turkcell.publisher_service.application.command;

import com.turkcell.publisher_service.application.dto.DeletedPublisherResponse;
import com.turkcell.publisher_service.cqrs.Command;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record DeletePublisherCommand(@NotBlank UUID id) implements Command<DeletedPublisherResponse> {
}
