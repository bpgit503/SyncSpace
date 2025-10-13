package com.devbp.syncspace.exceptions;

public class InvalidTrainerEarningsException extends ResourceNotFoundException {
    public InvalidTrainerEarningsException(String message) {
        super(message);
    }

    public InvalidTrainerEarningsException(String message, String fieldName) {
        super(message, fieldName);
    }
}
