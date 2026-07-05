package com.upc.innovify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HistorialSesionDTO {
    private Integer idSesion;
    private String nombreAprendiz;
    private String materia;
    private LocalDateTime fecha;
}