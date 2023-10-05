package com.midas.nuevatienda.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "Payload CrearProducto - Endpoint /")
public class ProductoRequest {
    private String productoName;
    private String descripcion;
    private Double precio;
    private Integer stock;
}
