package com.midas.nuevatienda.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orden_compra")
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordenCompraId;
    private String address;
    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "carrito_compras_ID", referencedColumnName = "carritoId")
    private CarritoCompras carritoCompras;


}