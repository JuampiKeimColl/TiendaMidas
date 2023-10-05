package com.midas.nuevatienda.exceptions;

import com.midas.nuevatienda.controller.CodigoRespuestaEnum;

public class ProductoInexistenteException extends BaseException {
    public ProductoInexistenteException() {
        super(CodigoRespuestaEnum.PRODUCTO_INEXISTENTE);
    }
}
