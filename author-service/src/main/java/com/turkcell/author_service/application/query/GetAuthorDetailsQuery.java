package com.turkcell.author_service.application.query;

import com.turkcell.author_service.application.dto.AuthorDetails;
import com.turkcell.author_service.core.cqrs.Query;

import java.util.UUID;

public record GetAuthorDetailsQuery(UUID id) implements Query<AuthorDetails> {
}
