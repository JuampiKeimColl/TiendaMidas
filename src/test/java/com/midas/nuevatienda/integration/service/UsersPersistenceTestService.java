package com.midas.nuevatienda.integration.service;

import com.midas.nuevatienda.persistence.entity.Cliente;
import com.midas.nuevatienda.persistence.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersPersistenceTestService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public void clearRegisters(){
        clienteRepository.deleteAll();
    }
}
