package com.midas.nuevatienda.mapper;

import com.midas.nuevatienda.dto.ProductoDTO;
import com.midas.nuevatienda.persistence.entity.Producto;
import com.midas.nuevatienda.request.ProductoRequest;
import com.midas.nuevatienda.response.ProductoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductoMapper {
    ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);

    @Mapping(source = "name", target = "productoName")
    Producto toProducto(String name, String description, Double price, Integer stock);

    @Mapping(source = "productoRequest.productoName", target = "productoName")
    Producto toProducto(ProductoRequest productoRequest);

    ProductoDTO toProductoDTO(Producto producto);

    ProductoResponse toProductoResponse(ProductoDTO producto);
}
