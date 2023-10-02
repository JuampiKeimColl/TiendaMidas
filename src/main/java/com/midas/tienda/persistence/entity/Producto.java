package com.midas.tienda.persistence.entity;

import com.midas.tienda.enums.Estado;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
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
    @ToString.Exclude
    private Set<CarritoCompras> carritoCompras = new HashSet<>();


}
