package com.upc.innovify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReporteVerificacionesDTO {
    private Long verificados;
    private Long pendientes;
    private Long rechazados;
    private Long totalEstudiantes;
}