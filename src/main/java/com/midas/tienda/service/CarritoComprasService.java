package com.midas.tienda.service;

import com.midas.tienda.persistence.entity.CarritoCompras;
import com.midas.tienda.persistence.entity.Producto;
import com.midas.tienda.persistence.repository.CarritoComprasRepository;
import com.midas.tienda.persistence.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
public class CarritoComprasService {
    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    CarritoComprasRepository carritoComprasRepository;

    @Transactional
    public CarritoCompras agregarProductoAlCarrito(Long productoId, Integer cantidad){
        Optional<CarritoCompras> carritoRta = carritoComprasRepository.buscarCarritoActivoPorIdDeUsuario(String.valueOf(productoId));
        if (carritoRta.isEmpty()){
            CarritoCompras carrito =new CarritoCompras();
            Optional<Producto> productoRta  = productoRepository.findById(productoId);

            if(productoRta.isPresent()){
                Producto producto = productoRta.get();
                producto.setCantidad(cantidad);

                carrito.setProductos((List<Producto>) producto);
                return carritoComprasRepository.save(carrito);

            }

        }
        CarritoCompras carrito = carritoRta.get();
        Optional<Producto> productoRta  = productoRepository.findById(productoId);
        List<Producto> producto = (List<Producto>) productoRta.get();

        carrito.setProductos(producto);

        return carritoComprasRepository.save(carrito);

    }

//            Optional<Producto> productoOptional = productoRepository.findById(productoId);
//
//            if (productoOptional.isPresent()) {
//                Producto producto = productoOptional.get();
//                producto.setCarritoCompras((Set<CarritoCompras>) carrito); // Asigna el carrito al producto
//                //productos.add(producto);
//            } else {
//                throw new NoSuchElementException("No se encontró un producto con el ID proporcionado: " + productoId);
//            }
//
//        return carrito;


    public void nuevoCarritoCompras (){
        CarritoCompras carritoCompras = new CarritoCompras();
        carritoComprasRepository.save(carritoCompras);

    }

//    @Transactional
//    public void agregarProductoAlCarrito(List<Long> productoIds, String clienteId) {
//        // Verifica si el usuario tiene un carrito de compras activo
//        //CarritoCompras carrito = carritoComprasRepository.buscarCarritoActivoPorIdDeUsuario(clienteId); // Implementa esta lógica según tus necesidades
//
//        // Si no hay un carrito de compras activo, crea uno nuevo
//        if (carrito == null) {
//            carrito = new CarritoCompras();
//            // Configura cualquier otra información del carrito, como el usuario propietario, fecha, etc.
//        }
//
//        List<Producto> productos = new ArrayList<>();
//
//        for (Long productoId : productoIds) {
//            Optional<Producto> productoOptional = productoRepository.findProductoAltaById(productoId);
//
//            if (productoOptional.isPresent()) {
//                Producto producto = productoOptional.get();
//                producto.setCarritoCompras(carrito); // Asigna el carrito al producto
//                productos.add(producto);
//            } else {
//                throw new NoSuchElementException("No se encontró un producto con el ID proporcionado: " + productoId);
//            }
//        }
//
//        carrito.setProductos(productos); // Asigna los productos al carrito
//
//        // Guarda o actualiza el carrito de compras en la base de datos
//        carritoComprasRepository.save(carrito);
//    }
//
//    private CarritoCompras obtenerCarritoActivoDelUsuario() {
//
//    }
//
//    @Transactional
//    public void crearOrdenDesdeCarrito(String address) {
//        CarritoCompras carrito = obtenerCarritoActivoDelUsuario();
//
//        if (carrito != null) {
//            OrdenCompra ordenCompra = new OrdenCompra();
//            ordenCompra.setAddress(address);
//            ordenCompra.setProductos(carrito.getProductos());
//
//            // Configura cualquier otra información de la orden, como el usuario propietario, fecha, etc.
//
//            // Guarda la orden en la base de datos
//            pedidosRepository.save(ordenCompra);
//
//            // Marca el carrito como inactivo o elimínalo, según tu lógica
//            carrito.setActivo(false);
//            carritoComprasRepository.save(carrito);
//        } else {
//            throw new NoSuchElementException("No se encontró un carrito de compras activo para convertir en orden.");
//        }
//    }


}
