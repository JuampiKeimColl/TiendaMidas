package com.midas.nuevatienda.persistence.entity;

import com.midas.nuevatienda.persistence.entity.enums.Rol;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(
        name = "clientes",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_correo", columnNames = {"email"})
        }
)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;
    private String clienteName;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<OrdenCompra> ordenCompra = new java.util.ArrayList<>();


}
