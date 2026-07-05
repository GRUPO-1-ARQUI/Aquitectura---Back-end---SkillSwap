package com.upc.innovify.controller;

import com.upc.innovify.dto.ReporteVerificacionesDTO;
import com.upc.innovify.model.Verificacion;
import com.upc.innovify.service.VerificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/verificaciones")
@RequiredArgsConstructor
public class VerificacionController {

    private final VerificacionService verificacionService;

    @GetMapping("/pendientes")
    public ResponseEntity<List<Verificacion>> getPendientes() {
        return ResponseEntity.ok(verificacionService.getPendientes());
    }

    @PostMapping
    public ResponseEntity<Verificacion> create(@Valid @RequestBody Verificacion verificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(verificacionService.create(verificacion));
    }

    @GetMapping("/estudiantes-verificacion/{idUsuario}")
    public ResponseEntity<Map<String, Object>> getEstudiante(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(verificacionService.getEstudiante(idUsuario));
    }

    @PutMapping("/{id}/aprobar")
    public ResponseEntity<Verificacion> aprobar(@PathVariable Integer id) {
        return ResponseEntity.ok(verificacionService.aprobar(id));
    }

    @PutMapping("/{id}/rechazar")
    public ResponseEntity<Verificacion> rechazar(@PathVariable Integer id) {
        return ResponseEntity.ok(verificacionService.rechazar(id));
    }

    // US44 - Aprobar verificaciones múltiples
    @PutMapping("/aprobar-multiples")
    public ResponseEntity<List<Verificacion>> aprobarMultiples(@RequestBody List<Integer> ids) {
        return ResponseEntity.ok(verificacionService.aprobarMultiples(ids));
    }
    // US43 - Reporte de verificaciones por institución
    @GetMapping("/reporte/institucion/{idInstitucion}")
    public ResponseEntity<ReporteVerificacionesDTO> getReportePorInstitucion(@PathVariable Integer idInstitucion) {
        return ResponseEntity.ok(verificacionService.getReportePorInstitucion(idInstitucion));
    }
}
