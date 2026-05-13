package com.upc.innovify.controller;

import com.upc.innovify.model.UsuarioHabilidad;
import com.upc.innovify.service.UsuarioHabilidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario-habilidades")
@RequiredArgsConstructor
public class UsuarioHabilidadController {
    private final UsuarioHabilidadService usuarioHabilidadService;

    // GET /api/usuario-habilidades — listar todas las asociaciones usuario-habilidad
    @GetMapping
    public List<UsuarioHabilidad> getAll() {
        return usuarioHabilidadService.getAll();
    }

    // GET /api/usuario-habilidades/usuario/{idUsuario} — habilidades de un usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<UsuarioHabilidad> getByUsuario(@PathVariable Integer idUsuario) {
        return usuarioHabilidadService.getByUsuario(idUsuario);
    }

    // POST /api/usuario-habilidades — asociar habilidad a un usuario
    @PostMapping
    public UsuarioHabilidad create(@RequestBody UsuarioHabilidad usuarioHabilidad) {
        return usuarioHabilidadService.create(usuarioHabilidad);
    }

    // DELETE /api/usuario-habilidades/{id} — quitar habilidad de un usuario
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        usuarioHabilidadService.delete(id);
    }
}