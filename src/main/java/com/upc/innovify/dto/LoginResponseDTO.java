package com.upc.innovify.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String token;
    private Integer idUsuario;
    private String nombreCompleto;
    private String correoInstitucional;
    private String rol;
}
