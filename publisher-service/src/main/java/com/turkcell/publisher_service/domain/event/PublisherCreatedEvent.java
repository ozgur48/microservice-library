package com.turkcell.publisher_service.domain.event;

import com.turkcell.publisher_service.domain.model.PublisherId;

import java.util.UUID;

public record PublisherCreatedEvent(PublisherId publisherId) {
    public PublisherCreatedEvent{
        if (publisherId == null){
            throw new IllegalArgumentException("publisherId cannot be null!");
        }
    }

}
