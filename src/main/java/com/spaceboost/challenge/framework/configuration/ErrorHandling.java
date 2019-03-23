package com.spaceboost.challenge.framework.configuration;

import com.spaceboost.challenge.infraestructure.exception.DuplicatedKeyException;
import com.spaceboost.challenge.infraestructure.exception.ForbiddenException;
import com.spaceboost.challenge.infraestructure.exception.ObjectNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DuplicatedKeyException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Duplicated key";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {ObjectNotFoundException.class})
    protected ResponseEntity<Object> notFound(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Entity not found";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {ForbiddenException.class})
    protected ResponseEntity<Object> forbidden(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Entity not found";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

}
