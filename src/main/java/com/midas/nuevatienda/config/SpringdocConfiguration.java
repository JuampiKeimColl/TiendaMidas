package com.midas.nuevatienda.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringdocConfiguration {
    @Bean
    public OpenAPI tiendaAPI() {

        return new OpenAPI().info(new Info()
                            .title("Tienda Midas")
                            .description("Esta es una tienda.")
                            .version("0.0.1")
                            .license(new License()
                            .name("JuanPablo")));
    }
}