package com.midas.nuevatienda.response;

import com.midas.nuevatienda.persistence.entity.CarritoCompras;
import com.midas.nuevatienda.persistence.entity.enums.Estado;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Schema(description = "response endpoints: /crearProducto")
public class ProductoResponse extends BaseResponse{
    private Long productoId;
    private String productoName;
    private String descripcion;
    private Double precio;
    private Integer cantidad;
    private Estado estado;
    private Integer stock;
    private Set<CarritoCompras> carritoCompras = new HashSet<>();
}
