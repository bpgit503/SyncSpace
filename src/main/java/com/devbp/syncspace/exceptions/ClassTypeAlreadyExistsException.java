package com.devbp.syncspace.exceptions;

public class ClassTypeAlreadyExistsException extends RuntimeException {

    public ClassTypeAlreadyExistsException(String message) {
        super(message);
    }

    public ClassTypeAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }


}
