package com.upc.innovify.controller;

import com.upc.innovify.model.Verificacion;
import com.upc.innovify.model.Usuario;
import com.upc.innovify.repository.UsuarioRepository;
import com.upc.innovify.service.VerificacionService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/verificaciones")
public class VerificacionController {

    private final VerificacionService verificacionService;
    private final UsuarioRepository usuarioRepository;

    public VerificacionController(VerificacionService verificacionService,
                                  UsuarioRepository usuarioRepository) {
        this.verificacionService = verificacionService;
        this.usuarioRepository = usuarioRepository;
    }

    // GET /api/verificaciones/pendientes — listar solicitudes de verificación pendientes // HU24
    @GetMapping("/pendientes")
    public List<Verificacion> getPendientes() {
        return verificacionService.getPendientes();
    }

    // POST /api/verificaciones — crear solicitud de verificación de estudiante
    @PostMapping
    public Verificacion create(@RequestBody Verificacion verificacion) {
        return verificacionService.create(verificacion);
    }

    // PUT /api/verificaciones/{id}/estado?estado= — aprobar o rechazar verificación // HU24
    @PutMapping("/{id}/estado")
    public Verificacion updateEstado(@PathVariable Integer id,
                                     @RequestParam String estado) {
        return verificacionService.updateEstado(id, estado);
    }

    // GET /api/verificaciones/estudiantes-verificacion/{idUsuario} — datos del estudiante para el detalle // HU24
    @GetMapping("/estudiantes-verificacion/{idUsuario}")
    public Map<String, Object> getEstudiante(@PathVariable Integer idUsuario) {

        Usuario estudiante = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Map<String, Object> response = new HashMap<>();
        response.put("idUsuario", estudiante.getIdUsuario());
        response.put("nombre", estudiante.getNombreCompleto());
        response.put("email", estudiante.getCorreoInstitucional());
        response.put("estado", estudiante.getEstado());
        response.put("verificado", estudiante.getVerificado());

        return response;
    }
}