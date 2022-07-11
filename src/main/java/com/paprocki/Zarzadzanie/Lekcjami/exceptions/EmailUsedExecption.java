package com.paprocki.Zarzadzanie.Lekcjami.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmailUsedExecption extends Exception {
    private final HttpStatus httpStatus;

    public EmailUsedExecption(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
