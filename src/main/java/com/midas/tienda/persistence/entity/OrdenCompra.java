package com.midas.tienda.persistence.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordenCompraId;
    private String address;

    @ManyToOne()
    private Cliente cliente;

    @OneToOne()
    @JoinColumn(name = "carritoComprasId", referencedColumnName = "carritoId")
    private CarritoCompras carritoCompras;

}
