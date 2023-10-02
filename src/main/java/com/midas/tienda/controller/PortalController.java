package com.midas.tienda.controller;

import com.midas.tienda.persistence.entity.Cliente;
import com.midas.tienda.exceptions.MiException;
import com.midas.tienda.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@Tag(name = "LoginApi", description = "Api para iniciar sesión.")
@Slf4j
public class PortalController {
    @Autowired
    private ClienteService clienteService;
    @GetMapping("/")
    public String index(){
        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "registro.html";
    }

    @PostMapping("/registro")
    @Operation(summary = "Método para crear un usuario.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))})})
    public String registro(@RequestParam String name,@RequestParam String email, @RequestParam String password,
                           @RequestParam String password2, ModelMap modelo) {
        log.info("Creando Cliente: " + name);
        try {
            clienteService.registrar(name, email, password, password2);
            modelo.put("exito", "Usuario registrado con éxito.");
            log.info("Cliente creado con éxito: " + name + " " + email);
            return "index.html";
        } catch (MiException ex){
            modelo.put("error", ex.getMessage());
            modelo.put("nombre",name);
            modelo.put("email",email);
            log.info("Se produjo un error al registrar los datos: " + ex.getMessage());
            return "registro.html";
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){
        if (error!=null){
            modelo.put("error", "Usuario o contraseña incorrecto");
            log.info("Usuario o contraseña incorrectos: ");
        }

        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/inicio")
    @Operation(summary = "Método para redireccionar al usuario o al admin.", responses = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "406", description = "Not Acceptable", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))}),
            @ApiResponse(responseCode = "500", description = "Generic Error", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Error.class))})})
    public String inicio(HttpSession session){
        Cliente logueado = (Cliente) session.getAttribute("usersession");
        if (logueado.getRol().toString().equals("ADMIN")){
            log.info("Ingreso de usuario ADMIN.");
            return "redirect:/admin/dashboard";
        }
        log.info("Ingreso de usuario USER.");
        return "inicio.html";
    }

}
