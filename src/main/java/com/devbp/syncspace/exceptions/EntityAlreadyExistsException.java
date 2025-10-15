package com.devbp.syncspace.exceptions;

public class EntityAlreadyExistsException extends ResourceNotFoundException {
    public EntityAlreadyExistsException(String message, String field) {
        super(message);
    }
}
