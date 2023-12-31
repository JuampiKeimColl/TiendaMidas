package com.midas.nuevatienda.dto;

import com.midas.nuevatienda.persistence.entity.CarritoCompras;
import com.midas.nuevatienda.persistence.entity.enums.Estado;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class ProductoDTO {
    private Long productoId;
    private String productoName;
    private String descripcion;
    private Double precio;
    private Integer cantidad;
    private Estado estado;
    private Integer stock;
    private Set<CarritoCompras> carritoCompras = new HashSet<>();
}
