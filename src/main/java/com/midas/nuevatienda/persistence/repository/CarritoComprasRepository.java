package com.midas.nuevatienda.persistence.repository;

import com.midas.nuevatienda.persistence.entity.CarritoCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoComprasRepository extends JpaRepository<CarritoCompras, Long> {
    @Query("SELECT c.carritoId FROM CarritoCompras c WHERE c.cliente= :clienteId")
    Optional<CarritoCompras> buscarCarritoActivoPorIdDeUsuario(@Param("clienteId")String clienteId);
    //SELECT c FROM Cliente c WHERE c.email = :email AND c.password = :password

    @Query("SELECT c.carritoId FROM CarritoCompras c WHERE c.cliente.clienteId = :clienteId")
    Optional<CarritoCompras> findCarritoCliente(@Param("clienteId")String clienteId);
}
