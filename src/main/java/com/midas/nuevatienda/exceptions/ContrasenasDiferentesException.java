package com.midas.nuevatienda.exceptions;

import com.midas.nuevatienda.controller.CodigoRespuestaEnum;

public class ContrasenasDiferentesException extends BaseException {

    public ContrasenasDiferentesException() {
        super(CodigoRespuestaEnum.ERROR_CONTRASENAS_DIFERENTES);
    }
}
