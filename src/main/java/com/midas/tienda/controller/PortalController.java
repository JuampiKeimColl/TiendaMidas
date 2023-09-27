package com.midas.tienda.controller;

import com.midas.tienda.entities.Client;
import com.midas.tienda.exceptions.MyException;
import com.midas.tienda.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class PortalController {
    private Logger log = Logger.getLogger(PortalController.class.getName());
    @Autowired
    private ClientService clientService;
    @GetMapping("/")
    public String index(){
        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String name,@RequestParam String email, @RequestParam String password,
                           @RequestParam String password2, ModelMap modelo) {
        log.info("Creando Cliente: " + name);
        try {
            clientService.registrar(name, email, password, password2);
            modelo.put("exito", "Usuario registrado con éxito.");
            log.info("Cliente creado con éxito: " + name + " " + email);
            return "index.html";
        } catch (MyException ex){
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
    public String inicio(HttpSession session){
        Client logueado = (Client) session.getAttribute("usersession");
        if (logueado.getRol().toString().equals("ADMIN")){
            log.info("Ingreso de usuario ADMIN.");
            return "redirect:/admin/dashboard";
        }
        log.info("Ingreso de usuario USER.");
        return "inicio.html";
    }

}
