package org.project.book.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class RestExceptionAdvice {
    @Order(1)
    @ExceptionHandler
    public ResponseEntity<Void> exceptionHandler(Exception e) {
        log.error(e);
        return new ResponseEntity<>(null, HttpStatus.NOT_IMPLEMENTED);
    }
}