package com.turkcell.publisher_service.application.command;

import com.turkcell.publisher_service.application.dto.UpdatedPublisherResponse;
import com.turkcell.publisher_service.cqrs.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdatePublisherCommand(@NotNull UUID id,
                                     @NotBlank String name,
                                     @NotBlank String address) implements Command<UpdatedPublisherResponse> {
}
