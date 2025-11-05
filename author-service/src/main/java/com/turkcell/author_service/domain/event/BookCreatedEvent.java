package com.turkcell.author_service.domain.event;

import java.util.UUID;

public record BookCreatedEvent(UUID bookId, String title) {
}
