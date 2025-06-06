package org.project.book.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class RestExceptionAdvice {
    private static final String optimisticLockingFailureMessage = "系统繁忙，请稍后再试";

    @ExceptionHandler(exception = OptimisticLockingFailureException.class)
    public ResponseEntity<String> optimisticLockingFailureException(OptimisticLockingFailureException ex) {
        return new ResponseEntity<>(optimisticLockingFailureMessage, HttpStatus.CONFLICT);
    }


    @ExceptionHandler
    public ResponseEntity<Void> exceptionHandler(Exception e) {
        log.error(e);
        return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
    }
}