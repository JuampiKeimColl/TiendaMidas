package com.midas.nuevatienda.dto;

import com.midas.nuevatienda.persistence.entity.enums.Estado;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class ProductoDto {
//    @NotNull(message = "El campo Nombre es obligatorio.")
    @NotEmpty(message = "El nombre del usuario es obligatorio")
    @Pattern(regexp = "", message = "El valor del campo número mensaje debe ser numérico sin decimales.")
    private String productoName;

    @NotEmpty(message = "El nombre del usuario es obligatorio")
    private String descripcion;

    @NotEmpty(message = "El nombre del usuario es obligatorio")
    private Double precio;

    @NotEmpty(message = "El nombre del usuario es obligatorio")
    private Integer cantidad;

//    @NotEmpty(message = "El nombre del usuario es obligatorio")
//    private Estado estado;

    @NotEmpty(message = "El nombre del usuario es obligatorio")
    private Integer stock;
}
