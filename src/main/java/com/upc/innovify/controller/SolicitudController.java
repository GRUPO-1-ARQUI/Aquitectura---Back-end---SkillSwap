package com.upc.innovify.controller;

import com.upc.innovify.model.Solicitud;
import com.upc.innovify.service.SolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.upc.innovify.model.Notificacion;
import com.upc.innovify.service.NotificacionService;

@RestController
@RequestMapping("/api/solicitudes")
@RequiredArgsConstructor
public class SolicitudController {
    private final SolicitudService solicitudService;
    private final NotificacionService notificacionService;

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
        Solicitud nueva = solicitudService.create(solicitud);

        Notificacion notificacion = new Notificacion();
        notificacion.setIdUsuario(solicitud.getIdTutor());
        notificacion.setTipo("solicitud");
        notificacion.setContenido("Tienes una nueva solicitud de asesoría");
        notificacion.setLeido(false);
        notificacionService.create(notificacion);

        return nueva;
    }

    // PUT /api/solicitudes/{id}/estado?estado= — cambiar estado (pendiente/aceptada/rechazada) // HU15 HU16
    @PutMapping("/{id}/estado")
    public Solicitud updateEstado(@PathVariable Integer id, @RequestParam String estado) {
        Solicitud solicitud = solicitudService.updateEstado(id, estado);

        if ("aceptado".equals(estado)) {
            Notificacion notificacion = new Notificacion();
            notificacion.setIdUsuario(solicitud.getIdAprendiz());
            notificacion.setTipo("aceptacion");
            notificacion.setContenido("Tu solicitud de asesoría ha sido aceptada");
            notificacion.setLeido(false);
            notificacionService.create(notificacion);
        }

        return solicitud;
    }
}