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

    // GET /api/historial-creditos/usuario/{idUsuario} — movimientos de créditos de un usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<HistorialCredito> getByUsuario(@PathVariable Integer idUsuario) {
        return historialCreditoService.getByUsuario(idUsuario);
    }

    // POST /api/historial-creditos — registrar movimiento de créditos
    @PostMapping
    public HistorialCredito create(@RequestBody HistorialCredito historialCredito) {
        return historialCreditoService.create(historialCredito);
    }
}