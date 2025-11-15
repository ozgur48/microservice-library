package com.turkcell.publisher_service.application.query;

import com.turkcell.publisher_service.application.dto.PublisherDetails;
import com.turkcell.publisher_service.cqrs.Query;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GetPublisherDetailsQuery(@NotNull UUID id) implements Query<PublisherDetails> {
}
