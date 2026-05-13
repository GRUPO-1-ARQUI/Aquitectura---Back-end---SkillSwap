package com.upc.innovify.controller;

import com.upc.innovify.model.Disponibilidad;
import com.upc.innovify.service.DisponibilidadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/disponibilidad")
@RequiredArgsConstructor
public class DisponibilidadController {
    private final DisponibilidadService disponibilidadService;

    // GET /api/disponibilidad — listar todos los horarios de disponibilidad
    @GetMapping
    public List<Disponibilidad> getAll() {
        return disponibilidadService.getAll();
    }

    // GET /api/disponibilidad/usuario/{idUsuario} — disponibilidad de un tutor
    @GetMapping("/usuario/{idUsuario}")
    public List<Disponibilidad> getByUsuario(@PathVariable Integer idUsuario) {
        return disponibilidadService.getByUsuario(idUsuario);
    }

    // POST /api/disponibilidad — registrar horario de disponibilidad
    @PostMapping
    public Disponibilidad create(@Valid @RequestBody Disponibilidad disponibilidad) {
        return disponibilidadService.create(disponibilidad);
    }

    // PUT /api/disponibilidad/{id} — actualizar horario de disponibilidad
    @PutMapping("/{id}")
    public Disponibilidad update(@PathVariable Integer id, @Valid @RequestBody Disponibilidad disponibilidad) {
        return disponibilidadService.update(id, disponibilidad);
    }

    // DELETE /api/disponibilidad/{id} — eliminar horario de disponibilidad
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        disponibilidadService.delete(id);
    }
}