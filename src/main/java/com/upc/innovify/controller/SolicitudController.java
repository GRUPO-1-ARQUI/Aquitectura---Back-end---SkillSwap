package com.upc.innovify.controller;

import com.upc.innovify.model.Solicitud;
import com.upc.innovify.service.SolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {

    private final SolicitudService solicitudService;

    @GetMapping
    public List<Solicitud> getAll() {
        return solicitudService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitud> getById(@PathVariable Integer id) {
        return solicitudService.getById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Solicitud no encontrada con id: " + id));
    }

    @GetMapping("/tutor/{idTutor}")
    public List<Solicitud> getByTutor(@PathVariable Integer idTutor) {
        return solicitudService.getByTutor(idTutor);
    }

    @GetMapping("/aprendiz/{idAprendiz}")
    public List<Solicitud> getByAprendiz(@PathVariable Integer idAprendiz) {
        return solicitudService.getByAprendiz(idAprendiz);
    }

    @PostMapping
    public ResponseEntity<Solicitud> create(@RequestBody Solicitud solicitud) {
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitudService.create(solicitud));
    }

    @PutMapping("/{id}/estado")
    public Solicitud updateEstado(@PathVariable Integer id, @RequestParam String estado) {
        return solicitudService.updateEstado(id, estado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        solicitudService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
