package com.evaluacion.intuit.client.infrastructure.input.advice;

import com.evaluacion.intuit.client.application.exceptions.NotFoundException;
import com.evaluacion.intuit.client.application.exceptions.OperationNotValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(NotFoundException ex) {
        log.info(ex.getMessage());
        log.debug(ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorResponse(ex.messageId, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(OperationNotValidException.class)
    public ResponseEntity<ErrorResponse> notValid(OperationNotValidException ex) {
        log.info(ex.getMessage());
        log.debug(ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorResponse(ex.messageId, ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }
}
