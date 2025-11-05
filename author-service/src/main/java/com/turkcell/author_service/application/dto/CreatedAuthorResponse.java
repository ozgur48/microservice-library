package com.turkcell.author_service.application.dto;

import java.util.UUID;

public record CreatedAuthorResponse(UUID authorId, String name, String surname) {
}
