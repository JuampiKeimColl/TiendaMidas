package com.midas.nuevatienda.service;

import com.midas.nuevatienda.exceptions.MiExceptions;
import com.midas.nuevatienda.persistence.entity.Producto;
import com.midas.nuevatienda.persistence.entity.enums.Estado;
import com.midas.nuevatienda.persistence.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public Producto crearProducto(String name, String description, Double price, Integer stock) throws MiExceptions {
        Producto producto = new Producto();
        validarProducto(name, description, price, stock);

        producto.setProductoName(name);
        producto.setDescripcion(description);
        producto.setPrecio(price);
        producto.setStock(stock);
        producto.setEstado(Estado.ALTA);

        return productoRepository.save(producto);

    }

    private void validarProducto(String name, String description, Double price, Integer count) throws MiExceptions{
        if(name.isEmpty()){
            throw new MiExceptions("El nombre es obligatorio.", HttpStatus.BAD_REQUEST);
        }
        if(description.isEmpty()){
            throw new MiExceptions("La descripción es obligatoria.", HttpStatus.BAD_REQUEST);
        }
        if(price == null){
            throw new MiExceptions("El precio es obligatorio.", HttpStatus.BAD_REQUEST);
        }
        if(count == null){
            throw new MiExceptions("La cantidad es obligatoria.", HttpStatus.BAD_REQUEST);
        }

    }

    @Transactional
    public void modificarProducto(Long productoId, String productoName, String descripcion, Double precio,
                                                  Integer stock) throws MiExceptions{
        Optional<Producto> rta  = productoRepository.findById(productoId);

        if(rta.isPresent()){
            Producto producto = rta.get();
            producto.setProductoName(productoName);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);

            productoRepository.save(producto);

        } else {
            throw new MiExceptions("Producto no encontrado.", HttpStatus.NOT_FOUND);
        }

    }

    @Transactional
    public void deleteById(Long productoId) throws MiExceptions{
        Optional<Producto> rta  = productoRepository.findById(productoId);

        if(rta.isPresent()){
            productoRepository.deleteById(productoId);

        } else {
            throw new MiExceptions("Producto no encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    public List<Producto> listarProductos() throws MiExceptions{
        return productoRepository.findAll();
    }
}
