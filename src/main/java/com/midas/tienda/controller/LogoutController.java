package com.midas.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
@RequestMapping("/logout")
public class LogoutController {
    private Logger log = Logger.getLogger(LogoutController.class.getName());

    @GetMapping
    public String logout(HttpServletRequest request) {

        request.getSession().invalidate();
        log.info("Cierre de sesión exitoso.");
        return "redirect:/login";
    }
}
