package com.midas.tienda.exceptions;

import lombok.Data;

@Data
public class MiException extends Exception{
       public MiException(String message) {
        super(message);
    }

}
