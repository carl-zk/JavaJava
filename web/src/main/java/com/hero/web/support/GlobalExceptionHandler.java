package com.hero.web.support;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Result> defaultHandler(ServiceException ex) {
        return new ResponseEntity<>(Result.error(ex.getErrorCode(), ex.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Result> defaultHandler(ConstraintViolationException ex) {
        return new ResponseEntity<>(Result.error(400, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
