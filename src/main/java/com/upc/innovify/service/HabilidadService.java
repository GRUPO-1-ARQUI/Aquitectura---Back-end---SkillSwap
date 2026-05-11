package com.upc.innovify.service;

import com.upc.innovify.model.Habilidad;
import com.upc.innovify.repository.HabilidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HabilidadService {
    private final HabilidadRepository habilidadRepository;

    public List<Habilidad> getAll() {
        return habilidadRepository.findAll();
    }

    public Optional<Habilidad> getById(Integer id) {
        return habilidadRepository.findById(id);
    }

    public Habilidad create(Habilidad habilidad) {
        return habilidadRepository.save(habilidad);
    }

    public Habilidad update(Integer id, Habilidad habilidad) {
        habilidad.setIdHabilidad(id);
        return habilidadRepository.save(habilidad);
    }

    public void delete(Integer id) {
        habilidadRepository.deleteById(id);
    }
}