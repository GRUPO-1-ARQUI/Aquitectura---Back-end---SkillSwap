package com.upc.innovify.controller;

import com.upc.innovify.model.Notificacion;
import com.upc.innovify.service.NotificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {
    private final NotificacionService notificacionService;

    // GET /api/notificaciones/usuario/{idUsuario} — todas las notificaciones de un usuario // HU10 HU15
    @GetMapping("/usuario/{idUsuario}")
    public List<Notificacion> getByUsuario(@PathVariable Integer idUsuario) {
        return notificacionService.getByUsuario(idUsuario);
    }

    // GET /api/notificaciones/usuario/{idUsuario}/no-leidas — solo notificaciones no leídas // HU10 HU15
    @GetMapping("/usuario/{idUsuario}/no-leidas")
    public List<Notificacion> getNoLeidas(@PathVariable Integer idUsuario) {
        return notificacionService.getNoLeidas(idUsuario);
    }

    // POST /api/notificaciones — crear notificación (tipo, contenido, idUsuario, leido) // HU10 HU15
    @PostMapping
    public Notificacion create(@Valid @RequestBody Notificacion notificacion) {
        return notificacionService.create(notificacion);
    }

    // PUT /api/notificaciones/{id}/leer — marcar notificación como leída // HU10
    @PutMapping("/{id}/leer")
    public Notificacion marcarLeida(@PathVariable Integer id) {
        return notificacionService.marcarLeida(id);
    }
}