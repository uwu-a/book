package org.project.book.advice;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionAdvice {
    @Order(1)
    @ExceptionHandler
    public ResponseEntity<Void> exceptionHandler(Exception e) {
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }
}