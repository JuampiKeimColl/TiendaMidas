package com.midas.tienda.service;

import com.midas.tienda.exceptions.MiException;
import com.midas.tienda.persistence.entity.CarritoCompras;
import com.midas.tienda.persistence.entity.OrdenCompra;
import com.midas.tienda.persistence.repository.CarritoComprasRepository;
import com.midas.tienda.persistence.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public OrdenCompra crearOrden(CarritoCompras carritoCompras, String address) throws MiException {
        Optional<CarritoCompras> rta = carritoComprasRepository.findById(carritoCompras.getCarritoId());

        if (rta.isEmpty()){
            throw new MiException("El Carrito de Compras no existe o fue dado de baja.");
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
