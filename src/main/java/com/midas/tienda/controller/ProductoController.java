package com.midas.tienda.controller;

import com.midas.tienda.exceptions.MiException;
import com.midas.tienda.persistence.entity.Producto;
import com.midas.tienda.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/producto")
@Tag(name = "ProductoApi", description = "Api para ABM productos")
@Slf4j
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/listaProductos")
    public String listaProducto(){
        return "lista_productos.html";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "producto.html";
    }

    @PostMapping(path = "/crearProducto", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Método para crear un producto.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))})})
    public Producto crearProducto(@RequestParam String name, @RequestParam String description, @RequestParam Double price,
                                  @RequestParam Integer stock) throws MiException {
        log.info("Creando Producto: " + name);

//        try {
//            productoService.crearProducto(name, description, price, stock);
            //modelo.put("exito", "Producto registrado con éxito.");
            log.info("Producto creado con éxito: " + name + " " + description);

            return productoService.crearProducto(name, description, price, stock);
//        } catch (MiException ex){
//            modelo.put("error", ex.getMessage());
//            modelo.put("nombre",name);
//            modelo.put("description",description);
//            modelo.put("price", price);
//            modelo.put("stock", stock);

//            log.info("Se produjo un error al registrar los datos: " + ex.getMessage());
//            return "producto.html";

        }



    @PostMapping("/editarProducto")
    @Operation(summary = "Método para editar un producto.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))})})
    public String editarProducto(@RequestParam Long productId, @RequestParam String productName, @RequestParam String descripcion, @RequestParam Double precio,
                                @RequestParam Integer stock, ModelMap modelo){


        try {
            productoService.modificarProducto(productId, productName, descripcion, precio, stock);

            return "lista_productos.html";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre",productName);
            modelo.put("description",descripcion);
            modelo.put("price", precio);
            modelo.put("count", stock);

            log.info("Se produjo un error al modificar los datos: " + ex.getMessage());
            return "producto.html";
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

        return ResponseEntity.noContent().build();

    }

}
