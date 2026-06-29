package com.upc.innovify.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .info(new Info()
                        .title("SkillSwap API")
                        .version("1.0.0")
                        .description("""
                                API REST para SkillSwap, la plataforma de intercambio de habilidades entre
                                estudiantes universitarios desarrollada por Innovify.

                                Permite el registro y autenticación de usuarios (US01, US02), gestión de
                                solicitudes de tutoría con validación interuniversitaria (US05), programación
                                de sesiones con acreditación automática de créditos al completarse (US51),
                                reseñas, mensajería, notificaciones, historial de créditos y verificación de
                                estudiantes por coordinadores (US24).

                                Endpoints públicos: POST /api/usuarios · POST /api/usuarios/login
                                El resto requiere autenticación JWT en el header: Authorization: Bearer {token}
                                """))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Token JWT obtenido en POST /api/usuarios/login. "
                                        + "Cópialo aquí con el formato: Bearer {token}")));
    }
}
