package com.upc.innovify.dto;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Integer idUsuario;
    private String nombreCompleto;
    private String correoInstitucional;
    private Integer idInstitucion;
    private String rol;
    private Integer creditos;
    private Double reputacionPromedio;
    private String biografia;
    private String estado;
    private Boolean verificado;
    private Boolean enLinea;
    private Boolean disponible;

}