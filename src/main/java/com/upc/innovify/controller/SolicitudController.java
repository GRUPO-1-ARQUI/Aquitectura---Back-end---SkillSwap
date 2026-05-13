package com.upc.innovify.controller;

import com.upc.innovify.model.Solicitud;
import com.upc.innovify.service.SolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {
    private final SolicitudService solicitudService;

    // GET /api/solicitudes — listar todas las solicitudes
    @GetMapping
    public List<Solicitud> getAll() {
        return solicitudService.getAll();
    }

    // GET /api/solicitudes/{id} — obtener solicitud por ID
    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> getById(@PathVariable Integer id) {
        return solicitudService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/solicitudes/tutor/{idTutor} — solicitudes recibidas por un tutor // HU15 HU16
    @GetMapping("/tutor/{idTutor}")
    public List<Solicitud> getByTutor(@PathVariable Integer idTutor) {
        return solicitudService.getByTutor(idTutor);
    }

    // GET /api/solicitudes/aprendiz/{idAprendiz} — solicitudes enviadas por un aprendiz // HU10
    @GetMapping("/aprendiz/{idAprendiz}")
    public List<Solicitud> getByAprendiz(@PathVariable Integer idAprendiz) {
        return solicitudService.getByAprendiz(idAprendiz);
    }

    // POST /api/solicitudes — crear nueva solicitud de tutoría // HU15
    @PostMapping
    public Solicitud create(@RequestBody Solicitud solicitud) {
        return solicitudService.create(solicitud);
    }

    // PUT /api/solicitudes/{id}/estado?estado= — cambiar estado (pendiente/aceptada/rechazada) // HU15 HU16
    @PutMapping("/{id}/estado")
    public Solicitud updateEstado(@PathVariable Integer id, @RequestParam String estado) {
        return solicitudService.updateEstado(id, estado);
    }
}