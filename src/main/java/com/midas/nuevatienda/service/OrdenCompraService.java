package com.midas.nuevatienda.service;

import com.midas.nuevatienda.exceptions.MiExceptions;
import com.midas.nuevatienda.persistence.entity.CarritoCompras;
import com.midas.nuevatienda.persistence.entity.OrdenCompra;
import com.midas.nuevatienda.persistence.repository.CarritoComprasRepository;
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
    @Transactional
    public OrdenCompra crearOrden(CarritoCompras carritoCompras, String address) throws MiExceptions {
        Optional<CarritoCompras> rta = carritoComprasRepository.findById(carritoCompras.getCarritoId());

        if (rta.isEmpty()){
            throw new MiExceptions("El Carrito de Compras no existe o fue dado de baja.", HttpStatus.NOT_FOUND);
        }
        OrdenCompra ordenCompra = new OrdenCompra();
        ordenCompra.setCarritoCompras(carritoCompras);
        ordenCompra.setAddress(address);
        ordenCompra.setCliente(carritoCompras.getCliente());

        return ordenCompraRepository.save(ordenCompra);
    }

    public List<OrdenCompra> listarPedidos() {
        return ordenCompraRepository.findAll();
    }
}
