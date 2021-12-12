package com.carl.web.support;

import com.carl.web.common.Result;
import com.carl.web.common.ServiceException;
import com.example.validator.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

/**
 * @author carl
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    public static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result defaultHandler(ServiceException ex) {
        return Result.error(ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class, ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result defaultHandler(RuntimeException ex) {
        return Result.error(400, ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result defaultHandler(EntityNotFoundException ex) {
        return Result.error(404, ex.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result defaultHandler(Throwable ex) {
        LOG.error("server error", ex);
        return Result.error(500, ex.getMessage());
    }
}
