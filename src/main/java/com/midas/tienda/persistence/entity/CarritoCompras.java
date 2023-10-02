package com.midas.tienda.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class CarritoCompras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carritoId;
    private Boolean activo = true;
    @ManyToMany
    @JoinTable(
            name = "carrito_producto",
            joinColumns = @JoinColumn(name = "carrito_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos = new ArrayList<>();

    @OneToOne
    private Cliente cliente;

    @OneToOne(mappedBy = "carritoCompras", cascade = CascadeType.ALL, orphanRemoval = true)
    private OrdenCompra ordenCompra;

}
