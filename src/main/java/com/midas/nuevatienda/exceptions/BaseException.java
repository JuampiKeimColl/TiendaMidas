package com.midas.nuevatienda.exceptions;

import com.midas.nuevatienda.controller.CodigoRespuestaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends Exception{

    private String codigo;
    private String descripcion;

    public BaseException(CodigoRespuestaEnum error) {
        this.codigo = error.getCodigo();
        this.descripcion = error.getDescripcion();
    }
}
