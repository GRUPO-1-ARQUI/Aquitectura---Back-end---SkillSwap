package com.upc.innovify.controller;

import com.upc.innovify.model.Habilidad;
import com.upc.innovify.service.HabilidadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habilidades")
@RequiredArgsConstructor
public class HabilidadController {
    private final HabilidadService habilidadService;

    // GET /api/habilidades — listar todas las habilidades
    @GetMapping
    public List<Habilidad> getAll() {
        return habilidadService.getAll();
    }

    // GET /api/habilidades/{id} — obtener habilidad por ID
    @GetMapping("/{id}")
    public ResponseEntity<Habilidad> getById(@PathVariable Integer id) {
        return habilidadService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/habilidades — crear habilidad
    @PostMapping
    public Habilidad create(@Valid @RequestBody Habilidad habilidad) {
        return habilidadService.create(habilidad);
    }

    // PUT /api/habilidades/{id} — actualizar habilidad
    @PutMapping("/{id}")
    public Habilidad update(@PathVariable Integer id, @Valid @RequestBody Habilidad habilidad) {
        return habilidadService.update(id, habilidad);
    }

    // DELETE /api/habilidades/{id} — eliminar habilidad
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        habilidadService.delete(id);
    }
}