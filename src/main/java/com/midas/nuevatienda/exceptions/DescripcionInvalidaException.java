package com.midas.nuevatienda.exceptions;

import com.midas.nuevatienda.controller.CodigoRespuestaEnum;

public class DescripcionInvalidaException extends BaseException {
    public DescripcionInvalidaException(){
        super(CodigoRespuestaEnum.DESCRIPCION_INVALIDA);
    }
}
