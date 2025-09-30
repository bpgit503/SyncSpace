package com.devbp.syncspace.exceptions;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private Map<String, String> fieldErrors = new HashMap<>();

    public ResourceNotFoundException(String message) {
        super(message);
        addFieldError("", message);
    }
    public ResourceNotFoundException(String message, String fieldName) {
        super(message);
        addFieldError(fieldName, message);
    }

    public void addFieldError(String field, String errorMessage) {
        fieldErrors.put(field, errorMessage);
    }

}
