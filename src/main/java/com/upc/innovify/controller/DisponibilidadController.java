package com.upc.innovify.controller;

import com.upc.innovify.model.Disponibilidad;
import com.upc.innovify.service.DisponibilidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/disponibilidad")
@RequiredArgsConstructor
public class DisponibilidadController {
    private final DisponibilidadService disponibilidadService;

    @GetMapping
    public List<Disponibilidad> getAll() {
        return disponibilidadService.getAll();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Disponibilidad> getByUsuario(@PathVariable Integer idUsuario) {
        return disponibilidadService.getByUsuario(idUsuario);
    }

    @PostMapping
    public Disponibilidad create(@RequestBody Disponibilidad disponibilidad) {
        return disponibilidadService.create(disponibilidad);
    }

    @PutMapping("/{id}")
    public Disponibilidad update(@PathVariable Integer id, @RequestBody Disponibilidad disponibilidad) {
        return disponibilidadService.update(id, disponibilidad);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        disponibilidadService.delete(id);
    }
}