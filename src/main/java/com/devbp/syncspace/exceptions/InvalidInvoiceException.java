package com.devbp.syncspace.exceptions;

public class InvalidInvoiceException extends ResourceNotFoundException {
    public InvalidInvoiceException(String message) {
        super(message);
    }

    public InvalidInvoiceException(String message, String fieldName) {
        super(message, fieldName);
    }
}
