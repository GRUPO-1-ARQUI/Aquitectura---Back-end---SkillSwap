package com.upc.innovify.controller;

import com.upc.innovify.dto.DashboardEstudianteDTO;
import com.upc.innovify.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${ip.frontend}", allowedHeaders = "true", exposedHeaders = "Authorization")
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    // US34 - Dashboard principal del estudiante
    @GetMapping("/estudiante/{idEstudiante}")
    public ResponseEntity<DashboardEstudianteDTO> getDashboard(@PathVariable Integer idEstudiante) {
        return ResponseEntity.ok(dashboardService.getDashboardEstudiante(idEstudiante));
    }
}