package com.midas.nuevatienda.exceptions;

import com.midas.nuevatienda.controller.CodigoRespuestaEnum;

public class ParametrosIncorrectosException extends BaseException {

    public ParametrosIncorrectosException() {
        super(CodigoRespuestaEnum.ERROR_PARAMETROS_INCORRECTOS);
    }
}
