package com.midas.tienda.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@Tag(name = "VistaAdminnApi", description = "Api para mostrar inicio de admin.")
@Slf4j
public class AdminController {
    @GetMapping("/dashboard")
    @Operation(summary = "Método para mostrar el panel del admin.")
    public String panelAdmin(){
        return "panel.html";
    }

}
