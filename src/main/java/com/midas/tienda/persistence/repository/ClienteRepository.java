package com.midas.tienda.persistence.repository;

import com.midas.tienda.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    public Cliente buscarPorEmail(@Param("email")String email);
}
