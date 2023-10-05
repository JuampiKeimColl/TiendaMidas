package com.midas.nuevatienda.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodigoRespuestaEnum {
    OK("00","OK"),
    ERROR_NOMBRE_INVALIDO("01","El nombre de usuario ingresado es invalido"),
    ERROR_NOMBRE_PRODUCTO_INVALIDO("02","El nombre del producto ingresado es invalido"),
    ERROR_CONTRASENAS_DIFERENTES("03","Las contraseñas ingresadas no son iguales"),
    ERROR_PARAMETROS_INCORRECTOS("04","Los datos de entrada son incorrectos"),
    ERROR_EMAIL_INVALIDO("05","Email invalido"),
    DESCRIPCION_INVALIDA("06", "Descripción invalida" ),
    PRECIO_INVALIDO("07", "Precio invalido"),
    ERROR_GENERICO("99","Error generico"),
    PRODUCTO_INEXISTENTE("08","El producto no existe o fue dado de baja" ),
    CLIENTE_INEXISTENTE("09", "El cliente no existe o fue dado de baja" ),
    CARRITO_INEXISTENTE("10", "El Carrito no existe o fue dado de baja" );

    private final String codigo;
    private final String descripcion;

}
