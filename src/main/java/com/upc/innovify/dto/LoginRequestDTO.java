package com.upc.innovify.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "El correo institucional es obligatorio")
    private String correoInstitucional;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}
