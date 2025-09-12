package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.ErrorResponse;
import com.devbp.syncspace.exceptions.EmailAlreadyExistsException;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(ResourceNotFoundException ex) {

        log.error("Resource not found: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .time(LocalDateTime.now())
                .message("RESOURCE_NOT_FOUND")
                .details("The Resource that your are searching for does not exist")
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EmailAlreadyExistsException ex) {

        log.error("Email already exists : {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .time(LocalDateTime.now())
                .message("EMAIL_ALREADY_EXISTS")
                .details("The email that you have entered already exists")
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


}
