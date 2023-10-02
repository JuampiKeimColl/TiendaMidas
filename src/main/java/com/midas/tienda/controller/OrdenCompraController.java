package com.midas.tienda.controller;

import com.midas.tienda.exceptions.MiException;
import com.midas.tienda.persistence.entity.CarritoCompras;
import com.midas.tienda.persistence.entity.OrdenCompra;
import com.midas.tienda.service.OrdenCompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ordenCompra")
@Tag(name = "OrdenCompraApi", description = "Api para una Orden de Compra.")
@Slf4j
public class OrdenCompraController {
    @Autowired
    OrdenCompraService ordenCompraService;

    @PostMapping("/crearOrden")
    @Operation(summary = "Método para crear una orden")
    public OrdenCompra crearOrden(@RequestBody CarritoCompras carritoCompras, String address) throws MiException {
        return ordenCompraService.crearOrden(carritoCompras, address);
    }

}
