package com.devbp.syncspace.exceptions;

public class InvalidUserTypeException extends ResourceNotFoundException {
    public InvalidUserTypeException(String message) {
        super(message);
    }

    public InvalidUserTypeException(String message, String fieldName) {
        super(message, fieldName);
    }
}
