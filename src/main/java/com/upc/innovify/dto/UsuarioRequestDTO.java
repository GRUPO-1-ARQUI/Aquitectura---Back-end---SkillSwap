package com.upc.innovify.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioRequestDTO {

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombreCompleto;

    @NotBlank(message = "El correo institucional es obligatorio")
    @Email(message = "El formato del correo es inválido")
    private String correoInstitucional;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotNull(message = "La institución es obligatoria")
    private Integer idInstitucion;

    @NotBlank(message = "El rol es obligatorio")
    private String rol;

    @Size(max = 500, message = "La biografía no puede exceder 500 caracteres")
    private String biografia;
}
