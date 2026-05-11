package com.upc.innovify.controller;

import com.upc.innovify.model.Habilidad;
import com.upc.innovify.service.HabilidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habilidades")
@RequiredArgsConstructor
public class HabilidadController {
    private final HabilidadService habilidadService;

    @GetMapping
    public List<Habilidad> getAll() {
        return habilidadService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habilidad> getById(@PathVariable Integer id) {
        return habilidadService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Habilidad create(@RequestBody Habilidad habilidad) {
        return habilidadService.create(habilidad);
    }

    @PutMapping("/{id}")
    public Habilidad update(@PathVariable Integer id, @RequestBody Habilidad habilidad) {
        return habilidadService.update(id, habilidad);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        habilidadService.delete(id);
    }
}