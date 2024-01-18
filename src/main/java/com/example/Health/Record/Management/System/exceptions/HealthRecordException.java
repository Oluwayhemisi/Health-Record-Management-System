package com.example.Health.Record.Management.System.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class HealthRecordException extends RuntimeException{
    private final HttpStatus httpStatus;

    public HealthRecordException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
