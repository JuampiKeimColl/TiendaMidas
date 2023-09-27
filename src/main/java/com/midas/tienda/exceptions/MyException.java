package com.midas.tienda.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class MyException extends Exception{
    private final String message;
    //private final HttpStatus httpStatus;

    public MyException(String message) {
        super(message);
        this.message = message;
        //this.httpStatus = httpStatus;
    }

}
