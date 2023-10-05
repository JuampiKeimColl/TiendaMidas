package com.midas.nuevatienda.controller;

import com.midas.nuevatienda.dto.ProductoDTO;
import com.midas.nuevatienda.exceptions.BaseException;
import com.midas.nuevatienda.exceptions.MiExceptions;
import com.midas.nuevatienda.mapper.ProductoMapper;
import com.midas.nuevatienda.persistence.entity.Producto;
import com.midas.nuevatienda.request.ProductoRequest;
import com.midas.nuevatienda.response.ErrorResponse;
import com.midas.nuevatienda.response.ProductoResponse;
import com.midas.nuevatienda.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/producto")
@Tag(name = "ProductoApi", description = "Api ABM de Producto.")
@Slf4j
public class ProductoController {
    @Autowired
    ProductoService productoService;

//    @PostMapping(path = "/crearProducto", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @Operation(summary = "Método para crear un producto.", responses = {
//            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
//                    schema = @Schema(implementation = ProductoResponse.class))}),
//            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
//                    schema = @Schema(implementation = ErrorResponse.class))}),
//            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
//                    schema = @Schema(implementation = ErrorResponse.class))}),
//            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
//                    schema = @Schema(implementation = ErrorResponse.class))})})
//    public Producto crearProducto(@RequestParam String name, @RequestParam String descripcion, @RequestParam Double precio,
//                                  @RequestParam Integer stock) throws MiExceptions {
//        log.info("Producto creado con éxito: " + name + " " + descripcion);
//        return productoService.crearProducto(name, descripcion, precio, stock);
//    }
    @PostMapping(value = "/crearProducto", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Método para crear un producto.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ProductoResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))})
        }
    )
    public ProductoResponse crearProducto(@Valid @NonNull @RequestBody ProductoRequest productoRequest) throws BaseException {
    log.info("Creando producto: " + productoRequest.getProductoName() + ", " + productoRequest.getDescripcion());
    ProductoDTO productoDTO = productoService.crearProducto(productoRequest);
    return ProductoMapper.INSTANCE.toProductoResponse(productoDTO);
}

    @PutMapping("/editarProducto")
    @Operation(summary = "Método para editar un producto.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ProductoResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))})
        }
    )
    public ResponseEntity<Void> editarProducto(@RequestParam Long productoId, @RequestParam String productoName, @RequestParam String descripcion, @RequestParam Double precio,
                                               @RequestParam Integer stock) throws BaseException {
        productoService.modificarProducto(productoId, productoName, descripcion, precio, stock);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{productoId}")
    @Operation(summary = "Método para eliminar un producto.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Producto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))})})
    public ResponseEntity<Void> delete(@PathVariable("productoId") Long productoId) throws BaseException {
        productoService.deleteById(productoId);
        //Para indicar que la operación se completó con éxito pero no se devuelve contenido en la respuesta
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listarProducto")
    @Operation(summary = "Método para listar productos.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Producto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))})})
    public List<Producto> listarProductos(){
        return productoService.listarProductos();
    }

}
