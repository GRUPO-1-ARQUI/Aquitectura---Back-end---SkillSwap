package com.upc.innovify.controller;

import com.upc.innovify.model.Institucion;
import com.upc.innovify.service.InstitucionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/instituciones")
@RequiredArgsConstructor
public class InstitucionController {
    private final InstitucionService institucionService;

    // GET /api/instituciones — listar todas las instituciones
    @GetMapping
    public List<Institucion> getAll() {
        return institucionService.getAll();
    }

    // GET /api/instituciones/{id} — obtener institución por ID
    @GetMapping("/{id}")
    public ResponseEntity<Institucion> getById(@PathVariable Integer id) {
        return institucionService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/instituciones — crear institución
    @PostMapping
    public Institucion create(@RequestBody Institucion institucion) {
        return institucionService.create(institucion);
    }

    // PUT /api/instituciones/{id} — actualizar institución
    @PutMapping("/{id}")
    public Institucion update(@PathVariable Integer id, @RequestBody Institucion institucion) {
        return institucionService.update(id, institucion);
    }

    // DELETE /api/instituciones/{id} — eliminar institución
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        institucionService.delete(id);
    }
}