package com.midas.nuevatienda.exceptions;

import com.midas.nuevatienda.controller.CodigoRespuestaEnum;

public class NombreProductoInvalidoException extends BaseException {
    public NombreProductoInvalidoException(){
        super(CodigoRespuestaEnum.ERROR_NOMBRE_PRODUCTO_INVALIDO);
    }
}
