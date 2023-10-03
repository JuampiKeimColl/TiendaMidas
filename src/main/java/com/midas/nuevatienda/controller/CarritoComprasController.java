package com.midas.nuevatienda.controller;

import com.midas.nuevatienda.persistence.entity.CarritoCompras;
import com.midas.nuevatienda.service.CarritoComprasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrito")
@Tag(name = "CarritoComprasApi", description = "Api para crear Carrito de Compras.")
@Slf4j
public class CarritoComprasController {
    @Autowired
    CarritoComprasService carritoComprasService;

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
    public CarritoCompras agregarProductoAlCarrito(Long productoId, Long clienteId, Integer cantidad ){
        return carritoComprasService.agregarProductoAlCarrito(productoId, clienteId, cantidad);

    }
}
