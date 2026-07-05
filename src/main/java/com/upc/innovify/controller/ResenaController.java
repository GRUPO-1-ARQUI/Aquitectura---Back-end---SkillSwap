package com.upc.innovify.controller;

import com.upc.innovify.model.Resena;
import com.upc.innovify.service.ResenaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
@RequiredArgsConstructor
public class ResenaController {

    private final ResenaService resenaService;

    // GET /api/resenas/usuario/{idUsuario} — reseñas recibidas por un usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<Resena> getByEvaluado(@PathVariable Integer idUsuario) {
        return resenaService.getByEvaluado(idUsuario);
    }

    // GET /api/resenas/usuario/{idUsuario}/promedio — rating promedio de un usuario
    @GetMapping("/usuario/{idUsuario}/promedio")
    public Double getPromedio(@PathVariable Integer idUsuario) {
        return resenaService.getPromedioPorUsuario(idUsuario);
    }

    // POST /api/resenas — crear reseña para un usuario
    @PostMapping
    public Resena create(@Valid @RequestBody Resena resena) {
        return resenaService.create(resena);
    }
}