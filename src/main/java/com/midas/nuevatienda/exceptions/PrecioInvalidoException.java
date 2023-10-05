package com.midas.nuevatienda.exceptions;

import com.midas.nuevatienda.controller.CodigoRespuestaEnum;

public class PrecioInvalidoException extends BaseException {
    public PrecioInvalidoException(){
        super(CodigoRespuestaEnum.PRECIO_INVALIDO);
    }
}
