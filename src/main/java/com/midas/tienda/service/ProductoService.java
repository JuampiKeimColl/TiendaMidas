package com.midas.tienda.service;

import com.midas.tienda.enums.Estado;
import com.midas.tienda.persistence.entity.Producto;
import com.midas.tienda.exceptions.MiException;
import com.midas.tienda.persistence.repository.ClienteRepository;
import com.midas.tienda.persistence.repository.OrdenCompraRepository;
import com.midas.tienda.persistence.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Transactional
    public String crearProducto(String name, String description, Double price, Integer stock) throws MiException {
            Producto producto = new Producto();
            validarProducto(name, description, price, stock);

            producto.setProductoName(name);
            producto.setDescripcion(description);
            producto.setPrecio(price);
            producto.setStock(stock);
            producto.setEstado(Estado.ALTA);

            productoRepository.save(producto);

        return name;
    }

    @Transactional
    public void modificarProducto(Long productoId, String productoName, String descripcion, Double precio,
                                  Integer stock) throws MiException{
        Optional<Producto> rta  = productoRepository.findById(productoId);

        if(rta.isPresent()){
            Producto producto = rta.get();
            producto.setProductoName(productoName);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);

            productoRepository.save(producto);

        }

    }

    public List<Producto> listaProductos(){

        return productoRepository.findAll();
    }

    @Transactional
    public void modificarEstado(Long productoId, String estado){
        Optional<Producto> rta  = productoRepository.findById(productoId);
        if(rta.isPresent()){
            if (estado.equalsIgnoreCase("ALTA")){

                Producto producto = rta.get();
                producto.setEstado(Estado.BAJA);

                productoRepository.save(producto);
            } else if (estado.equalsIgnoreCase("BAJA")) {
                Producto producto = rta.get();
                producto.setEstado(Estado.ALTA);

                productoRepository.save(producto);
            }

        }
    }

    private void validarProducto(String name, String description, Double price, Integer count) throws MiException{
        if(name.isEmpty()){
            throw new MiException("El nombre es obligatorio.");
        }
        if(description.isEmpty()){
            throw new MiException("La descripción es obligatoria.");
        }
        if(price == null){
            throw new MiException("El precio es obligatorio.");
        }
        if(count == null){
            throw new MiException("La cantidad es obligatoria.");
        }

    }
}
