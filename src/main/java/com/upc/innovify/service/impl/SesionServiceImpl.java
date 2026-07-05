package com.upc.innovify.service.impl;

import com.upc.innovify.dto.HistorialSesionDTO;
import com.upc.innovify.model.Habilidad;
import com.upc.innovify.model.Sesion;
import com.upc.innovify.model.Solicitud;
import com.upc.innovify.model.Usuario;
import com.upc.innovify.repository.HabilidadRepository;
import com.upc.innovify.repository.SesionRepository;
import com.upc.innovify.repository.SolicitudRepository;
import com.upc.innovify.repository.UsuarioRepository;
import com.upc.innovify.service.SesionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SesionServiceImpl implements SesionService {

    private final SesionRepository sesionRepository;
    private final SolicitudRepository solicitudRepository;
    private final UsuarioRepository usuarioRepository;
    private final HabilidadRepository habilidadRepository;

    @Override
    public List<Sesion> getAll() {
        return sesionRepository.findAll();
    }

    @Override
    public Optional<Sesion> getById(Integer id) {
        return sesionRepository.findById(id);
    }

    @Override
    public List<Sesion> getBySolicitud(Integer idSolicitud) {
        return sesionRepository.findByIdSolicitud(idSolicitud);
    }

    @Override
    public Sesion create(Sesion sesion) {
        sesion.setEstado("programada");
        return sesionRepository.save(sesion);
    }

    // US51 — Al marcar como "completada", acredita pagoCreditos al saldo del tutor
    @Override
    @Transactional
    public Sesion updateEstado(Integer id, String estado) {
        Sesion sesion = sesionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Sesión no encontrada con id: " + id));

        sesion.setEstado(estado);
        Sesion saved = sesionRepository.save(sesion);

        if ("completada".equals(estado)
                && sesion.getPagoCreditos() != null
                && sesion.getPagoCreditos() > 0) {

            Solicitud solicitud = solicitudRepository.findById(sesion.getIdSolicitud())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Solicitud vinculada no encontrada con id: " + sesion.getIdSolicitud()));

            Usuario tutor = usuarioRepository.findById(solicitud.getIdTutor())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Tutor no encontrado con id: " + solicitud.getIdTutor()));

            int base = tutor.getCreditos() != null ? tutor.getCreditos() : 0;
            tutor.setCreditos(base + sesion.getPagoCreditos());
            usuarioRepository.save(tutor);
        }

        return saved;
    }

    // US40 - Historial de sesiones completadas del tutor
    @Override
    public List<HistorialSesionDTO> getHistorialPorTutor(Integer idTutor) {

        List<Solicitud> solicitudes = solicitudRepository.findByIdTutor(idTutor);
        List<Integer> idsSolicitudes = solicitudes.stream()
                .map(Solicitud::getIdSolicitud)
                .toList();

        List<Sesion> sesiones = sesionRepository.findByIdSolicitudInAndEstado(idsSolicitudes, "completada");

        return sesiones.stream()
                .sorted(Comparator.comparing(Sesion::getFechaFin).reversed())
                .map(sesion -> {
                    Solicitud solicitud = solicitudes.stream()
                            .filter(s -> s.getIdSolicitud().equals(sesion.getIdSolicitud()))
                            .findFirst()
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Solicitud no encontrada"));

                    Usuario aprendiz = usuarioRepository.findById(solicitud.getIdAprendiz())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Aprendiz no encontrado"));

                    Habilidad habilidad = habilidadRepository.findById(solicitud.getIdHabilidad())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                    "Habilidad no encontrada"));

                    return new HistorialSesionDTO(
                            sesion.getIdSesion(),
                            aprendiz.getNombreCompleto(),
                            habilidad.getNombre(),
                            sesion.getFechaFin()
                    );
                })
                .toList();
    }
}