package com.midas.nuevatienda.service;

import com.midas.nuevatienda.exceptions.BaseException;
import com.midas.nuevatienda.exceptions.CarritoInexistenteException;
import com.midas.nuevatienda.exceptions.ClienteInexistenteException;
import com.midas.nuevatienda.exceptions.MiExceptions;
import com.midas.nuevatienda.persistence.entity.CarritoCompras;
import com.midas.nuevatienda.persistence.entity.Cliente;
import com.midas.nuevatienda.persistence.entity.OrdenCompra;
import com.midas.nuevatienda.persistence.repository.CarritoComprasRepository;
import com.midas.nuevatienda.persistence.repository.ClienteRepository;
import com.midas.nuevatienda.persistence.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenCompraService {
    @Autowired
    OrdenCompraRepository ordenCompraRepository;
    @Autowired
    CarritoComprasRepository carritoComprasRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Transactional
    public OrdenCompra crearOrden(Long carritoId, String address) throws BaseException {
        Optional<CarritoCompras> rtaCC = carritoComprasRepository.findById(carritoId);

        if (rtaCC.isEmpty()){
            throw new CarritoInexistenteException();
        }
        CarritoCompras carritoRta = rtaCC.get();
        Long id = carritoRta.getCliente().getClienteId();
        OrdenCompra ordenCompra = new OrdenCompra();
        ordenCompra.setCarritoCompras(carritoRta);
        ordenCompra.setAddress(address);

        Optional<Cliente> rtaC = clienteRepository.findById(id);
        if (rtaC.isEmpty()) {
            throw new ClienteInexistenteException();
        }
        Cliente clienteRta = rtaC.get();
        ordenCompra.setCliente(clienteRta);
        //clienteRta.setOrdenCompra((List<OrdenCompra>) ordenCompra);

        return ordenCompraRepository.save(ordenCompra);
    }

    public List<OrdenCompra> listarPedidos() {
        return ordenCompraRepository.findAll();
    }
}
