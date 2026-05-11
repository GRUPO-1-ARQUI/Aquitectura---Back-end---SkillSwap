package com.upc.innovify.controller;

import com.upc.innovify.model.Notificacion;
import com.upc.innovify.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {
    private final NotificacionService notificacionService;

    @GetMapping("/usuario/{idUsuario}")
    public List<Notificacion> getByUsuario(@PathVariable Integer idUsuario) {
        return notificacionService.getByUsuario(idUsuario);
    }

    @GetMapping("/usuario/{idUsuario}/no-leidas")
    public List<Notificacion> getNoLeidas(@PathVariable Integer idUsuario) {
        return notificacionService.getNoLeidas(idUsuario);
    }

    @PostMapping
    public Notificacion create(@RequestBody Notificacion notificacion) {
        return notificacionService.create(notificacion);
    }

    @PutMapping("/{id}/leer")
    public Notificacion marcarLeida(@PathVariable Integer id) {
        return notificacionService.marcarLeida(id);
    }
}