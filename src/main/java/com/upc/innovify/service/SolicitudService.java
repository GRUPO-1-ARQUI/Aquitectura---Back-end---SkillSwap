package com.upc.innovify.service;

import com.upc.innovify.model.Solicitud;
import com.upc.innovify.repository.SolicitudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SolicitudService {
    private final SolicitudRepository solicitudRepository;

    public List<Solicitud> getAll() {
        return solicitudRepository.findAll();
    }

    public Optional<Solicitud> getById(Integer id) {
        return solicitudRepository.findById(id);
    }

    public List<Solicitud> getByTutor(Integer idTutor) {
        return solicitudRepository.findByIdTutor(idTutor);
    }

    public List<Solicitud> getByAprendiz(Integer idAprendiz) {
        return solicitudRepository.findByIdAprendiz(idAprendiz);
    }

    public Solicitud create(Solicitud solicitud) {
        solicitud.setEstado("pendiente");
        solicitud.setFecha(LocalDateTime.now());
        return solicitudRepository.save(solicitud);
    }

    public Solicitud updateEstado(Integer id, String estado) {
        Solicitud solicitud = solicitudRepository.findById(id).orElseThrow();
        solicitud.setEstado(estado);
        return solicitudRepository.save(solicitud);
    }
}