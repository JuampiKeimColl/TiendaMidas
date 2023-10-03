package com.midas.nuevatienda.response;

import com.midas.nuevatienda.controller.CodigoRespuestaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private String codigoResultado = CodigoRespuestaEnum.OK.getCodigo();
    private String descripcionResultado = CodigoRespuestaEnum.OK.getDescripcion();
}
