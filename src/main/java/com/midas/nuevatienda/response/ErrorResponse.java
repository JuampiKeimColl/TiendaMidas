package com.midas.nuevatienda.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse extends BaseResponse {
    public ErrorResponse(String codigoResultado, String descripcionResultado) {
        super(codigoResultado, descripcionResultado);
    }
}
