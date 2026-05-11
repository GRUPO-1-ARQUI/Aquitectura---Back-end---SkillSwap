package com.upc.innovify.service;

import com.upc.innovify.model.Sesion;
import com.upc.innovify.repository.SesionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SesionService {
    private final SesionRepository sesionRepository;

    public List<Sesion> getAll() {
        return sesionRepository.findAll();
    }

    public Optional<Sesion> getById(Integer id) {
        return sesionRepository.findById(id);
    }

    public List<Sesion> getBySolicitud(Integer idSolicitud) {
        return sesionRepository.findByIdSolicitud(idSolicitud);
    }

    public Sesion create(Sesion sesion) {
        sesion.setEstado("programada");
        return sesionRepository.save(sesion);
    }

    public Sesion updateEstado(Integer id, String estado) {
        Sesion sesion = sesionRepository.findById(id).orElseThrow();
        sesion.setEstado(estado);
        return sesionRepository.save(sesion);
    }
}