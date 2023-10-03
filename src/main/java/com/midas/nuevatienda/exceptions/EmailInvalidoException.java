package com.midas.nuevatienda.exceptions;

import com.midas.nuevatienda.controller.CodigoRespuestaEnum;

public class EmailInvalidoException extends BaseException {

    public EmailInvalidoException() {
        super(CodigoRespuestaEnum.ERROR_EMAIL_INVALIDO);
    }
}
