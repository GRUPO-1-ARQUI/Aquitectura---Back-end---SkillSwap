package com.upc.innovify.service;

import com.upc.innovify.model.PlantillaMensaje;
import com.upc.innovify.repository.PlantillaMensajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantillaMensajeService {

    private final PlantillaMensajeRepository plantillaMensajeRepository;

    // Listar plantillas de un tutor
    public List<PlantillaMensaje> getByUsuario(Integer idUsuario) {
        return plantillaMensajeRepository.findByIdUsuario(idUsuario);
    }

    // Crear plantilla
    public PlantillaMensaje create(PlantillaMensaje plantilla) {
        return plantillaMensajeRepository.save(plantilla);
    }

    // Editar plantilla
    public PlantillaMensaje update(Integer id, PlantillaMensaje plantilla) {

        PlantillaMensaje existing = plantillaMensajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plantilla no encontrada"));

        existing.setTitulo(plantilla.getTitulo());
        existing.setContenido(plantilla.getContenido());

        return plantillaMensajeRepository.save(existing);
    }

    // Eliminar plantilla
    public void delete(Integer id) {
        plantillaMensajeRepository.deleteById(id);
    }
}