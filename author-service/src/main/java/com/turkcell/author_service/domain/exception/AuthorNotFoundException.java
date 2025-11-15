package com.turkcell.author_service.domain.exception;

import com.turkcell.author_service.domain.model.AuthorId;

import java.util.UUID;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(UUID id){
        super("Author, with: " + id + " could not find!");
    }
    public AuthorNotFoundException(AuthorId id){
        super("Author, with: " + id.value() + " could not find!");
    }
}
