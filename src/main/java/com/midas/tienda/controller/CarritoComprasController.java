package com.midas.tienda.controller;

import com.midas.tienda.persistence.entity.CarritoCompras;
import com.midas.tienda.service.CarritoComprasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carritoCompras")
@Tag(name = "CarritoComprasApi", description = "Api para crear carrito compra.")
@Slf4j
public class CarritoComprasController {
    @Autowired
    private CarritoComprasService carritoComprasService;


    @PostMapping(path ="/agregarProductoAlCarrito", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Método para agregar producto al carrito.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))})})
    public CarritoCompras agregarProductoAlCarrito(Long producto, Integer cantidad ){
         //carritoComprasService.agregarProductoAlCarrito(producto.getProductoId(), carritoCompras.getCarritoId());
        return carritoComprasService.agregarProductoAlCarrito(producto, cantidad);
    }
}
