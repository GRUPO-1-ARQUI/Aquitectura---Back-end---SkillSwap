package com.upc.innovify.controller;

import com.upc.innovify.model.Resena;
import com.upc.innovify.service.ResenaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
@RequiredArgsConstructor
public class ResenaController {

    private final ResenaService resenaService;

    @GetMapping("/usuario/{idUsuario}")
    public List<Resena> getByEvaluado(@PathVariable Integer idUsuario) {
        return resenaService.getByEvaluado(idUsuario);
    }

    @GetMapping("/usuario/{idUsuario}/promedio")
    public Double getPromedio(@PathVariable Integer idUsuario) {
        return resenaService.getPromedioPorUsuario(idUsuario);
    }

    @PostMapping
    public Resena create(@RequestBody Resena resena) {
        return resenaService.create(resena);
    }
}