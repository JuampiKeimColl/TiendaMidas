package com.midas.nuevatienda.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.midas.nuevatienda.dto.ProductoDTO;
import com.midas.nuevatienda.exceptions.*;
import com.midas.nuevatienda.mapper.ProductoMapper;
import com.midas.nuevatienda.persistence.entity.Producto;
import com.midas.nuevatienda.persistence.entity.enums.Estado;
import com.midas.nuevatienda.persistence.repository.ProductoRepository;
import com.midas.nuevatienda.request.ProductoRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public ProductoDTO crearProducto(ProductoRequest productoRequest) throws BaseException {
    return commonNewProduct(productoRequest);

}

    private ProductoDTO commonNewProduct(ProductoRequest productoRequest) throws BaseException{
        validarProducto(productoRequest);
        Producto producto = ProductoMapper.INSTANCE.toProducto(productoRequest);
        producto.setEstado(Estado.ALTA);

        return ProductoMapper.INSTANCE.toProductoDTO(productoRepository.save(producto));
}


    public void validarProducto(ProductoRequest productoRequest) throws BaseException{
        if(Strings.isEmpty(productoRequest.getProductoName())){
            throw new NombreProductoInvalidoException();
        }
        if(Strings.isEmpty(productoRequest.getDescripcion())){
            throw new DescripcionInvalidaException();
        }
        if(productoRequest.getPrecio() == 0.0){
            throw new PrecioInvalidoException();
        }

    }

    @Transactional
    public void modificarProducto(Long productoId, String productoName, String descripcion, Double precio,
                                                  Integer stock) throws BaseException{
        Optional<Producto> rta  = productoRepository.findById(productoId);

        if(rta.isPresent()){
            Producto producto = rta.get();
            producto.setProductoName(productoName);
            producto.setDescripcion(descripcion);
            producto.setPrecio(precio);
            producto.setStock(stock);

            productoRepository.save(producto);

        } else {
            throw new ProductoInexistenteException();
        }

    }

    @Transactional
    public void deleteById(Long productoId) throws BaseException{
        Optional<Producto> rta  = productoRepository.findById(productoId);

        if(rta.isPresent()){
            productoRepository.deleteById(productoId);

        } else {
            throw new ProductoInexistenteException();
        }
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }
}
