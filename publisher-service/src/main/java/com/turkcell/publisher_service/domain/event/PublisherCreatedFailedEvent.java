package com.turkcell.publisher_service.domain.event;

import java.util.UUID;

public record PublisherCreatedFailedEvent(UUID publisherId) {
    public PublisherCreatedFailedEvent{
        if (publisherId == null){
            throw new IllegalArgumentException("publisherId cannot be null!");
        }
    }
}
