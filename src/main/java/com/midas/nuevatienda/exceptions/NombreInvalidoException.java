package com.midas.nuevatienda.exceptions;

import com.midas.nuevatienda.controller.CodigoRespuestaEnum;

public class NombreInvalidoException extends BaseException {

    public NombreInvalidoException() {
        super(CodigoRespuestaEnum.ERROR_NOMBRE_INVALIDO);
    }
}
