package com.upc.innovify.service.impl;

import com.upc.innovify.dto.DashboardEstudianteDTO;
import com.upc.innovify.model.Favorito;
import com.upc.innovify.model.Sesion;
import com.upc.innovify.model.Solicitud;
import com.upc.innovify.model.Usuario;
import com.upc.innovify.repository.*;
import com.upc.innovify.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UsuarioRepository usuarioRepository;
    private final SolicitudRepository solicitudRepository;
    private final SesionRepository sesionRepository;
    private final FavoritoRepository favoritoRepository;

    @Override
    public DashboardEstudianteDTO getDashboardEstudiante(Integer idEstudiante) {

        Usuario estudiante = usuarioRepository.findById(idEstudiante)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Estudiante no encontrado con id: " + idEstudiante));

        // Solicitudes pendientes (donde el estudiante es el aprendiz)
        List<Solicitud> solicitudesEstudiante = solicitudRepository.findByIdAprendiz(idEstudiante);
        long solicitudesPendientes = solicitudesEstudiante.stream()
                .filter(s -> "pendiente".equals(s.getEstado()))
                .count();

        // Sesiones programadas para hoy
        List<Integer> idsSolicitudes = solicitudesEstudiante.stream()
                .map(Solicitud::getIdSolicitud)
                .toList();

        long sesionesHoy = idsSolicitudes.stream()
                .flatMap(idSol -> sesionRepository.findByIdSolicitud(idSol).stream())
                .filter(sesion -> "programada".equals(sesion.getEstado())
                        && sesion.getFechaInicio().toLocalDate().isEqual(LocalDate.now()))
                .count();

        // Tutores favoritos en línea (estado = "Activo")
        List<Favorito> favoritos = favoritoRepository.findByIdEstudiante(idEstudiante);
        List<String> tutoresFavoritosEnLinea = favoritos.stream()
                .map(f -> usuarioRepository.findById(f.getIdTutor()))
                .filter(java.util.Optional::isPresent)
                .map(java.util.Optional::get)
                .filter(tutor -> "Activo".equals(tutor.getEstado()))
                .map(Usuario::getNombreCompleto)
                .toList();

        return new DashboardEstudianteDTO(
                estudiante.getNombreCompleto(),
                solicitudesPendientes,
                sesionesHoy,
                tutoresFavoritosEnLinea
        );
    }
}