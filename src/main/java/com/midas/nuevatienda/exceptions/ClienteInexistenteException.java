package com.midas.nuevatienda.exceptions;

import com.midas.nuevatienda.controller.CodigoRespuestaEnum;

public class ClienteInexistenteException extends BaseException {
    public ClienteInexistenteException(){
        super(CodigoRespuestaEnum.CLIENTE_INEXISTENTE);
    }
}
