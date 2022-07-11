package com.paprocki.Zarzadzanie.Lekcjami.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailUsedExecption.class)
    public ResponseEntity<String> handleException(EmailUsedExecption ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus());
    }
}
