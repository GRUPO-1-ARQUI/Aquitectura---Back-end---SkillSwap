package com.upc.innovify.service;

import com.upc.innovify.model.Disponibilidad;
import com.upc.innovify.repository.DisponibilidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DisponibilidadService {
    private final DisponibilidadRepository disponibilidadRepository;

    public List<Disponibilidad> getAll() {
        return disponibilidadRepository.findAll();
    }

    public List<Disponibilidad> getByUsuario(Integer idUsuario) {
        return disponibilidadRepository.findByIdUsuario(idUsuario);
    }

    public Disponibilidad create(Disponibilidad disponibilidad) {
        return disponibilidadRepository.save(disponibilidad);
    }

    public Disponibilidad update(Integer id, Disponibilidad disponibilidad) {
        Disponibilidad existing = disponibilidadRepository.findById(id).orElseThrow();
        existing.setDiaSemana(disponibilidad.getDiaSemana());
        existing.setHoraInicio(disponibilidad.getHoraInicio());
        existing.setHoraFin(disponibilidad.getHoraFin());
        return disponibilidadRepository.save(existing);
    }

    public void delete(Integer id) {
        disponibilidadRepository.deleteById(id);
    }
}