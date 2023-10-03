package com.midas.nuevatienda.persistence.entity;

import com.midas.nuevatienda.persistence.entity.enums.Estado;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;
    private String productoName;
    private String descripcion;
    private Double precio;
    private Integer cantidad;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private Integer stock;

    @ManyToMany(mappedBy = "productos")
    private Set<CarritoCompras> carritoCompras = new HashSet<>();

}