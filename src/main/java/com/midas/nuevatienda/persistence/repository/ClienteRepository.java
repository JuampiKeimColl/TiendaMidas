package com.midas.nuevatienda.persistence.repository;

import com.midas.nuevatienda.persistence.entity.Cliente;
import com.midas.nuevatienda.persistence.entity.enums.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    public Cliente buscarPorEmail(@Param("email")String email);
    @Query("SELECT c FROM Cliente c WHERE c.rol = :rol")
    public List<Cliente> findAllByClienteRol(@Param("rol")Rol rol);
    @Query("SELECT c FROM Cliente c WHERE c.email = :email AND c.password = :password")
    public Cliente findUsuarioPassword(@Param("email")String email, @Param("password")String password);
}
