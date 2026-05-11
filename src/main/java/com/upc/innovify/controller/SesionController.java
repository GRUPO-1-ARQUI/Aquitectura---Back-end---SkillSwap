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

    @GetMapping
    public List<Sesion> getAll() {
        return sesionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sesion> getById(@PathVariable Integer id) {
        return sesionService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/solicitud/{idSolicitud}")
    public List<Sesion> getBySolicitud(@PathVariable Integer idSolicitud) {
        return sesionService.getBySolicitud(idSolicitud);
    }

    @PostMapping
    public Sesion create(@RequestBody Sesion sesion) {
        return sesionService.create(sesion);
    }

    @PutMapping("/{id}/estado")
    public Sesion updateEstado(@PathVariable Integer id, @RequestParam String estado) {
        return sesionService.updateEstado(id, estado);
    }
}