package com.turkcell.publisher_service.domain.exception;

import com.turkcell.publisher_service.domain.model.PublisherId;

import java.util.UUID;

public class PublisherNotFoundException extends RuntimeException{
    public PublisherNotFoundException(UUID id){
        super("Publisher, ID: " + id + "could not find.!");
    }
    public PublisherNotFoundException(PublisherId id){
        super("Publisher, ID: " + id + "could not find.!");
    }
}
