package com.midas.nuevatienda.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class MiExceptions extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public MiExceptions(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
