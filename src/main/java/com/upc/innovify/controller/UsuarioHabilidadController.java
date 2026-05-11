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

    @GetMapping
    public List<UsuarioHabilidad> getAll() {
        return usuarioHabilidadService.getAll();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<UsuarioHabilidad> getByUsuario(@PathVariable Integer idUsuario) {
        return usuarioHabilidadService.getByUsuario(idUsuario);
    }

    @PostMapping
    public UsuarioHabilidad create(@RequestBody UsuarioHabilidad usuarioHabilidad) {
        return usuarioHabilidadService.create(usuarioHabilidad);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        usuarioHabilidadService.delete(id);
    }
}