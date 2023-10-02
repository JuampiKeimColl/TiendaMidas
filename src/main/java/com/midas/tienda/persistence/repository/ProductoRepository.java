package com.midas.tienda.persistence.repository;

import com.midas.tienda.persistence.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
//    @Query("SELECT p FROM Producto p WHERE p.productoName = :productName")
//    public Producto buscarProductoNombre(@Param("productName") String productName);
//
//    @Query("SELECT p FROM Producto p WHERE p.cliente.clienteName = :clientName")
//    public List<Producto> buscarProductoCliente(@Param("clientName") String clientName);
//
//    @Query("SELECT p FROM Producto p WHERE p.productoId = :productId AND p.estado = 'ALTA'")
//    Optional<Producto> findProductoAltaById(@Param("productId") Long productId);
//
//    @Query("SELECT p FROM Producto p WHERE p.estado = 'ALTA'")
//    public List<Producto> listaProductosAlta();
}
