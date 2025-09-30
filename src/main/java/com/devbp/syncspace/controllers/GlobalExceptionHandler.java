package com.devbp.syncspace.controllers;

import com.devbp.syncspace.domain.dtos.ErrorResponse;
import com.devbp.syncspace.exceptions.ClassTypeAlreadyExistsException;
import com.devbp.syncspace.exceptions.EmailAlreadyExistsException;
import com.devbp.syncspace.exceptions.InvalidUserTypeException;
import com.devbp.syncspace.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(ResourceNotFoundException ex) {

        log.error("Resource not found: {}", ex.getMessage());

        ex.addFieldError("", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .time(LocalDateTime.now())
                .message("RESOURCE_NOT_FOUND")
                .details("The Resource that your are searching for does not exist")
                .fieldErrors(ex.getFieldErrors())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {

        log.error("Email already exists : {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .time(LocalDateTime.now())
                .message("EMAIL_ALREADY_EXISTS")
                .details("The email that you have entered already exists")
                .fieldErrors(Map.of("",ex.getMessage()))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidUserTypeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUserTypeException(InvalidUserTypeException ex) {

        log.error(" Invalid User Type provided: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .time(LocalDateTime.now())
                .message("INVALID_USER_TYPE")
                .details("The user that you have entered is not of the correct type")
                .fieldErrors(Map.of("", ex.getMessage()))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(ClassTypeAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleClassTypeAlreadyExistsException(ClassTypeAlreadyExistsException ex) {

        log.error("Class Type already exists: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.CONFLICT.value())
                .time(LocalDateTime.now())
                .message("CLASS_TYPE_ALREADY_EXISTS")
                .details("The class type you have entered already exists")
                .fieldErrors(Map.of("", ex.getMessage()))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.warn("Validation Error of type: {}", ex.getMessage());

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage()));

        ex.getBindingResult().getGlobalErrors().forEach(error ->
                fieldErrors.put(error.getObjectName(), error.getDefaultMessage()));

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .time(LocalDateTime.now())
                .message("FAILED_VALIDATION")
                .details("The input data does not match the validation constraints")
                .fieldErrors(fieldErrors)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


}
