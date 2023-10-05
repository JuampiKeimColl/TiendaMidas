package com.midas.nuevatienda.service;

import com.midas.nuevatienda.exceptions.*;
import com.midas.nuevatienda.persistence.entity.CarritoCompras;
import com.midas.nuevatienda.persistence.entity.Cliente;
import com.midas.nuevatienda.persistence.entity.Producto;
import com.midas.nuevatienda.persistence.repository.CarritoComprasRepository;
import com.midas.nuevatienda.persistence.repository.ClienteRepository;
import com.midas.nuevatienda.persistence.repository.ProductoRepository;
import com.midas.nuevatienda.request.ProductoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CarritoComprasService {
    @Autowired
    CarritoComprasRepository carritoComprasRepository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ClienteRepository clienteRepository;

//    @Transactional
//    public CarritoCompras agregarProductoAlCarrito(Long productoId, Long clienteId, Integer cantidad) throws MiExceptions {
//        Optional<CarritoCompras> carritoRta = carritoComprasRepository
//                .findCarritoCliente(String.valueOf(clienteId));
//
//        if (carritoRta.isEmpty()){
//            CarritoCompras carrito =new CarritoCompras();
//            Optional<Producto> productoRta  = productoRepository.findById(productoId);
//
//            if (productoRta.isPresent()) {
//                Producto producto = productoRta.get();
//                producto.setCantidad(cantidad);
//
//                List<Producto> productos = new ArrayList<>(); // Crea una nueva lista de productos
//                productos.add(producto); // Agrega el producto a la lista
//
//                carrito.setProductos(productos); // Establece la lista de productos en el carrito
//                return carritoComprasRepository.save(carrito);
//            } else {
//                throw new MiExceptions("Producto no encontrado.", HttpStatus.NOT_FOUND);
//            }
//
//        } else {
//            Optional<Producto> productoRta  = productoRepository.findById(productoId);
//            // Segunda parte del código
//            Producto producto = productoRta.get();
//            producto.setCantidad(cantidad);
//
//            List<Producto> productos = new ArrayList<>(); // Crea una nueva lista de productos
//            productos.add(producto); // Agrega el producto a la lista
//
//            CarritoCompras carrito = carritoRta.get();
//            carrito.setProductos(productos); // Establece la lista de productos en el carrito
//            return carritoComprasRepository.save(carrito);
//        }

//        Optional<Producto> productoRta  = productoRepository.findById(productoId);
//        if(productoRta.isPresent()){
//            Producto producto = productoRta.get();
//            producto.setCantidad(cantidad);
//
//            List<Producto> productoLista = (List<Producto>) producto;
//            carrito.setProductos(productoLista);
//
//        } else {
//            throw new MiExceptions("Producto no encontrado.", HttpStatus.NOT_FOUND);
//        }



//        return carritoComprasRepository.save(carrito);
//    }

    @Transactional
    public CarritoCompras agregarProductoAlCarrito1(Long productoId, Long clienteId, Integer cantidad) throws BaseException {
        Optional<CarritoCompras> carritoRta = carritoComprasRepository
                .findCarritoCliente(String.valueOf(clienteId));

        if (carritoRta.isEmpty()){
            CarritoCompras carrito =new CarritoCompras();
            Optional<Producto> productoRta  = productoRepository.findById(productoId);

            if (productoRta.isPresent()) {
                Producto producto = productoRta.get();
                producto.setCantidad(cantidad);

                List<Producto> productos = new ArrayList<>(); // Crea una nueva lista de productos
                productos.add(producto); // Agrega el producto a la lista

                carrito.setProductos(productos); // Establece la lista de productos en el carrito
                return carritoComprasRepository.save(carrito);
            } else {
                throw new ProductoInexistenteException();
            }

        } else {
            Optional<Producto> productoRta  = productoRepository.findById(productoId);
            // Segunda parte del código
            Producto producto = productoRta.get();
            producto.setCantidad(cantidad);

            List<Producto> productos = new ArrayList<>(); // Crea una nueva lista de productos
            productos.add(producto); // Agrega el producto a la lista

            CarritoCompras carrito = carritoRta.get();
            carrito.setProductos(productos); // Establece la lista de productos en el carrito
            return carritoComprasRepository.save(carrito);
        }
    }

    @Transactional
    public CarritoCompras agregarProductoAlCarrito(Long productoId, Long clienteId, Integer cantidad) throws BaseException {
        Optional<CarritoCompras> carritoRta = carritoComprasRepository.buscarCarritoActivoPorIdDeUsuario(clienteId);
        CarritoCompras carrito;

        if (carritoRta.isEmpty()) {
            carrito = new CarritoCompras();
        } else {
            carrito = carritoRta.get();
        }

        Optional<Producto> productoRta = productoRepository.findById(productoId);

        if (productoRta.isEmpty()) {
            throw new ProductoInexistenteException();
        }

        Producto producto = productoRta.get();
        producto.setCantidad(cantidad);

        List<Producto> productos = new ArrayList<>();
        productos.add(producto);
        Optional<Cliente> rtaC = clienteRepository.findById(clienteId);
        if (rtaC.isEmpty()) {
            throw new ClienteInexistenteException();
        }
        Cliente clienteRta = rtaC.get();
        carrito.setCliente(clienteRta);
        carrito.setProductos(productos);

        return carritoComprasRepository.save(carrito);
    }

}
