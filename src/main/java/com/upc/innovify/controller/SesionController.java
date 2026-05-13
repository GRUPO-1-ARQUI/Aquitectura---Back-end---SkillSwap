package com.upc.innovify.controller;

import com.upc.innovify.model.Sesion;
import com.upc.innovify.service.SesionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sesiones")
@RequiredArgsConstructor
public class SesionController {
    private final SesionService sesionService;

    // GET /api/sesiones — listar todas las sesiones
    @GetMapping
    public List<Sesion> getAll() {
        return sesionService.getAll();
    }

    // GET /api/sesiones/{id} — obtener sesión por ID
    @GetMapping("/{id}")
    public ResponseEntity<Sesion> getById(@PathVariable Integer id) {
        return sesionService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/sesiones/solicitud/{idSolicitud} — sesiones de una solicitud
    @GetMapping("/solicitud/{idSolicitud}")
    public List<Sesion> getBySolicitud(@PathVariable Integer idSolicitud) {
        return sesionService.getBySolicitud(idSolicitud);
    }

    // POST /api/sesiones — crear sesión de tutoría
    @PostMapping
    public Sesion create(@RequestBody Sesion sesion) {
        return sesionService.create(sesion);
    }

    // PUT /api/sesiones/{id}/estado?estado= — cambiar estado de la sesión
    @PutMapping("/{id}/estado")
    public Sesion updateEstado(@PathVariable Integer id, @RequestParam String estado) {
        return sesionService.updateEstado(id, estado);
    }
}