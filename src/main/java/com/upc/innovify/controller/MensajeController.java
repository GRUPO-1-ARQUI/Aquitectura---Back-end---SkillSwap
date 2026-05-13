package com.upc.innovify.controller;

import com.upc.innovify.model.Mensaje;
import com.upc.innovify.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mensajes")
@RequiredArgsConstructor
public class MensajeController {
    private final MensajeService mensajeService;

    // GET /api/mensajes/solicitud/{idSolicitud} — mensajes de una solicitud de tutoría
    @GetMapping("/solicitud/{idSolicitud}")
    public List<Mensaje> getBySolicitud(@PathVariable Integer idSolicitud) {
        return mensajeService.getBySolicitud(idSolicitud);
    }

    // POST /api/mensajes — enviar mensaje en una solicitud
    @PostMapping
    public Mensaje create(@RequestBody Mensaje mensaje) {
        return mensajeService.create(mensaje);
    }
}