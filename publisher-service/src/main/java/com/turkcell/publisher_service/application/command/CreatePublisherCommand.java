package com.turkcell.publisher_service.application.command;
import com.turkcell.publisher_service.application.dto.CreatedPublisherResponse;
import com.turkcell.publisher_service.cqrs.Command;
import jakarta.validation.constraints.NotBlank;

public record CreatePublisherCommand(@NotBlank String name,
                                     @NotBlank String address) implements Command<CreatedPublisherResponse> {
}
