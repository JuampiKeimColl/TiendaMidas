package com.midas.nuevatienda.controller;

import com.midas.nuevatienda.exceptions.BaseException;
import com.midas.nuevatienda.exceptions.MiExceptions;
import com.midas.nuevatienda.persistence.entity.CarritoCompras;
import com.midas.nuevatienda.persistence.entity.OrdenCompra;
import com.midas.nuevatienda.response.ErrorResponse;
import com.midas.nuevatienda.service.OrdenCompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordenCompra")
@Tag(name = "OrdenCompraApi", description = "Api para una Orden de Compra.")
@Slf4j
public class OrdenCompraController {
    @Autowired
    OrdenCompraService ordenCompraService;

    @PostMapping(value ="/crearOrden", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Método para crear Orden de Compra.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = OrdenCompra.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))})})
    public OrdenCompra crearOrden(@RequestParam Long carritoId, @RequestParam String address) throws BaseException {
        return ordenCompraService.crearOrden(carritoId, address);
    }

    @GetMapping(path ="/listarOrden")
    @Operation(summary = "Método para listar Ordenes de Compra.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = OrdenCompra.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))})})
    public List<OrdenCompra> listarPedidos(){
        return ordenCompraService.listarPedidos();
    }
}
