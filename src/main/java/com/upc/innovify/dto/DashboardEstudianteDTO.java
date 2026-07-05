package com.upc.innovify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DashboardEstudianteDTO {
    private String nombreEstudiante;
    private Long solicitudesPendientes;
    private Long sesionesHoy;
    private List<String> tutoresFavoritosEnLinea;
}