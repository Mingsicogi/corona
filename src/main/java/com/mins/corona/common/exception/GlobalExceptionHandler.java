package com.mins.corona.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity Exception(Exception e) {
        log.error("ERROR MESSAGE : {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity RuntimeException(RuntimeException e) {
        log.error("ERROR MESSAGE : {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(BadParameterException.class)
    public ResponseEntity BadParameterException(BadParameterException e) {
        log.warn("WARNING MESSAGE : {}", e.getMessage(), e);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(NotAuthException.class)
    public ResponseEntity NotAuthException(NotAuthException e) {
        log.warn("WARNING MESSAGE : {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
