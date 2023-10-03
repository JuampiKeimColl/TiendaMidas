package com.midas.nuevatienda.controller;

import com.midas.nuevatienda.dto.ClienteDTO;
import com.midas.nuevatienda.exceptions.BaseException;
import com.midas.nuevatienda.mapper.ClienteMapper;
import com.midas.nuevatienda.persistence.entity.Cliente;
import com.midas.nuevatienda.persistence.entity.enums.Rol;
import com.midas.nuevatienda.request.RegistroRequest;
import com.midas.nuevatienda.response.ClienteResponse;
import com.midas.nuevatienda.response.ErrorResponse;
import com.midas.nuevatienda.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@Tag(name = "ClienteApi", description = "Api para crear tipo de usuario.")
@Slf4j
@Validated
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping(value = "/registroUser",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Método para crear un USER.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}
            )
        }
    )
    public ClienteResponse crearClienteUser(@Valid @NonNull @RequestBody RegistroRequest registroRequest) throws BaseException {
        log.info("Creando USER... " + registroRequest.getName() + " " + registroRequest.getEmail());
        ClienteDTO clienteDTO =  clienteService.crearClienteUser(registroRequest);
        return ClienteMapper.INSTANCE.toClienteResponse(clienteDTO);
    }

    @PostMapping(value = "/registroAdmin",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Método para crear un ADMIN.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ClienteResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class))})
        }
    )
    public ClienteResponse crearClienteAdmin(@Valid @NonNull @RequestBody RegistroRequest registroRequest) throws BaseException {
        log.info("Creando ADMIN... " + registroRequest.getName() + " " + registroRequest.getEmail());
        ClienteDTO clienteDTO =  clienteService.crearClienteAdmin(registroRequest);
        return ClienteMapper.INSTANCE.toClienteResponse(clienteDTO);

    }

    @GetMapping("/login")
    @Operation(summary = "Inicio de sesión.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))})})
    public Cliente login(@RequestParam(required = false) String email, String password){
        if (email!=null){

            log.error("Usuario o contraseña incorrectos: " + email + " " + password);

        }
        return clienteService.loginUsuario(email, password);

    }

    @GetMapping("/listarUsuarios")
    @Operation(summary = "Método para listar todos los usuarios.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))})})
    public List<Cliente> listarUsuarios(){
        return clienteService.listarUsuarios();
    }

    @GetMapping("/roles{rol}")
    @Operation(summary = "Método para listar según rol.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))})})
    public List<Cliente> findByRol(@PathVariable("rol") Rol rol){
        return clienteService.findByRol(rol);
    }


}
