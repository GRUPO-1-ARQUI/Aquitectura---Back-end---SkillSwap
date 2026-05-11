package com.upc.innovify.controller;

import com.upc.innovify.model.HistorialCredito;
import com.upc.innovify.service.HistorialCreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/historial-creditos")
@RequiredArgsConstructor
public class HistorialCreditoController {
    private final HistorialCreditoService historialCreditoService;

    @GetMapping("/usuario/{idUsuario}")
    public List<HistorialCredito> getByUsuario(@PathVariable Integer idUsuario) {
        return historialCreditoService.getByUsuario(idUsuario);
    }

    @PostMapping
    public HistorialCredito create(@RequestBody HistorialCredito historialCredito) {
        return historialCreditoService.create(historialCredito);
    }
}