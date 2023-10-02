package com.midas.tienda.persistence.repository;

import com.midas.tienda.persistence.entity.CarritoCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoComprasRepository extends JpaRepository<CarritoCompras, Long> {

    @Query("SELECT c.carritoId FROM CarritoCompras c WHERE c.cliente= :clienteId")
    public Optional<CarritoCompras> buscarCarritoActivoPorIdDeUsuario(@Param("clienteId")String clienteId);
}
