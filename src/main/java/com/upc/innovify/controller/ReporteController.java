package com.upc.innovify.controller;

import com.upc.innovify.model.Reporte;
import com.upc.innovify.service.ReporteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    // GET /api/reportes/usuario/{idUsuarioReportado} — historial de reportes de un estudiante (US45)
    @GetMapping("/usuario/{idUsuarioReportado}")
    public List<Reporte> getByUsuarioReportado(@PathVariable Integer idUsuarioReportado) {
        return reporteService.getByUsuarioReportado(idUsuarioReportado);
    }

    // POST /api/reportes — crear un reporte de comportamiento inadecuado (US45)
    @PostMapping
    public Reporte create(@Valid @RequestBody Reporte reporte) {
        return reporteService.create(reporte);
    }
}