package com.midas.tienda.persistence.repository;

import com.midas.tienda.persistence.entity.OrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra,Long> {
}
