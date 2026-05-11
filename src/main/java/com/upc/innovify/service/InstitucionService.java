package com.upc.innovify.service;

import com.upc.innovify.model.Institucion;
import com.upc.innovify.repository.InstitucionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstitucionService {
    private final InstitucionRepository institucionRepository;

    public List<Institucion> getAll() {
        return institucionRepository.findAll();
    }

    public Optional<Institucion> getById(Integer id) {
        return institucionRepository.findById(id);
    }

    public Institucion create(Institucion institucion) {
        return institucionRepository.save(institucion);
    }

    public Institucion update(Integer id, Institucion institucion) {
        institucion.setIdInstitucion(id);
        return institucionRepository.save(institucion);
    }

    public void delete(Integer id) {
        institucionRepository.deleteById(id);
    }
}