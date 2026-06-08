package com.upc.innovify.service.impl;

import com.upc.innovify.model.Solicitud;
import com.upc.innovify.model.Usuario;
import com.upc.innovify.repository.SolicitudRepository;
import com.upc.innovify.repository.UsuarioRepository;
import com.upc.innovify.service.SolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Solicitud> getAll() {
        return solicitudRepository.findAll();
    }

    @Override
    public Optional<Solicitud> getById(Integer id) {
        return solicitudRepository.findById(id);
    }

    @Override
    public List<Solicitud> getByTutor(Integer idTutor) {
        return solicitudRepository.findByIdTutor(idTutor);
    }

    @Override
    public List<Solicitud> getByAprendiz(Integer idAprendiz) {
        return solicitudRepository.findByIdAprendiz(idAprendiz);
    }

    @Override
    public Solicitud create(Solicitud solicitud) {
        // US05 — Regla interuniversitaria: aprendiz y tutor no pueden ser de la misma institución
        Usuario aprendiz = usuarioRepository.findById(solicitud.getIdAprendiz())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Aprendiz no encontrado con id: " + solicitud.getIdAprendiz()));

        Usuario tutor = usuarioRepository.findById(solicitud.getIdTutor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Tutor no encontrado con id: " + solicitud.getIdTutor()));

        if (aprendiz.getIdInstitucion().equals(tutor.getIdInstitucion())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "No es posible crear la solicitud: el aprendiz (id=" + solicitud.getIdAprendiz() +
                    ") y el tutor (id=" + solicitud.getIdTutor() +
                    ") pertenecen a la misma institución (id=" + aprendiz.getIdInstitucion() + "). " +
                    "SkillSwap solo permite emparejamientos interuniversitarios.");
        }

        solicitud.setEstado("pendiente");
        solicitud.setFecha(LocalDateTime.now());
        return solicitudRepository.save(solicitud);
    }

    @Override
    public Solicitud updateEstado(Integer id, String estado) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Solicitud no encontrada con id: " + id));
        solicitud.setEstado(estado);
        return solicitudRepository.save(solicitud);
    }

    @Override
    public void delete(Integer id) {
        if (!solicitudRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Solicitud no encontrada con id: " + id);
        }
        solicitudRepository.deleteById(id);
    }
}
