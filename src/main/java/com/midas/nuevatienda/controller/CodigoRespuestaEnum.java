package com.midas.nuevatienda.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodigoRespuestaEnum {
    OK("00","OK"),
    ERROR_NOMBRE_INVALIDO("01","El nombre de usuario ingresado es invalido"),
    ERROR_CONTRASENAS_DIFERENTES("03","Las contraseñas ingresadas no son iguales"),
    ERROR_PARAMETROS_INCORRECTOS("04","Los datos de entrada son incorrectos"),
    ERROR_EMAIL_INVALIDO("05","Email invalido"),
    ERROR_GENERICO("99","Error generico");

    private String codigo;
    private String descripcion;

}
