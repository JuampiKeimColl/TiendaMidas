package com.midas.nuevatienda.exceptions;

import com.midas.nuevatienda.controller.CodigoRespuestaEnum;

public class CarritoInexistenteException extends BaseException {
    public CarritoInexistenteException(){
        super(CodigoRespuestaEnum.CARRITO_INEXISTENTE);
    }
}
