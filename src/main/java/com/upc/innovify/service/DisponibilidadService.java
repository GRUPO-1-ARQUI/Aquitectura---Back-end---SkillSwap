package com.upc.innovify.service;

import com.upc.innovify.model.Disponibilidad;
import com.upc.innovify.repository.DisponibilidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DisponibilidadService {

    private static final Set<String> DIAS_VALIDOS = Set.of(
            "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO"
    );

    private final DisponibilidadRepository disponibilidadRepository;

    public List<Disponibilidad> getAll() {
        return disponibilidadRepository.findAll();
    }

    public List<Disponibilidad> getByUsuario(Integer idUsuario) {
        return disponibilidadRepository.findByIdUsuario(idUsuario);
    }

    public Disponibilidad create(Disponibilidad disponibilidad) {
        validar(disponibilidad, null);
        return disponibilidadRepository.save(disponibilidad);
    }

    public Disponibilidad update(Integer id, Disponibilidad disponibilidad) {
        Disponibilidad existing = disponibilidadRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Disponibilidad no encontrada con id: " + id));

        // Para validar solapamiento necesitamos saber a qué usuario pertenece
        disponibilidad.setIdUsuario(existing.getIdUsuario());
        validar(disponibilidad, id);

        existing.setDiaSemana(disponibilidad.getDiaSemana());
        existing.setHoraInicio(disponibilidad.getHoraInicio());
        existing.setHoraFin(disponibilidad.getHoraFin());
        return disponibilidadRepository.save(existing);
    }

    public void delete(Integer id) {
        if (!disponibilidadRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Disponibilidad no encontrada con id: " + id);
        }
        disponibilidadRepository.deleteById(id);
    }

    private void validar(Disponibilidad nuevo, Integer idExcluido) {
        // 1. Día de la semana válido
        String diaNormalizado = normalizarDia(nuevo.getDiaSemana());
        if (!DIAS_VALIDOS.contains(diaNormalizado)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El día '" + nuevo.getDiaSemana() + "' no es válido. Usa: lunes, martes, miércoles, jueves, viernes, sábado o domingo.");
        }

        // 2. Rango de horas coherente
        if (!nuevo.getHoraFin().isAfter(nuevo.getHoraInicio())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La hora de fin debe ser posterior a la hora de inicio.");
        }

        // 3. Sin solapamiento con otros bloques del mismo tutor en el mismo día
        // (comparamos con el día normalizado, para que no falle por mayúsculas/tildes distintas)
        List<Disponibilidad> delMismoUsuario = disponibilidadRepository
                .findByIdUsuario(nuevo.getIdUsuario());

        boolean seSolapa = delMismoUsuario.stream()
                .filter(d -> idExcluido == null || !d.getIdDisponibilidad().equals(idExcluido))
                .filter(d -> normalizarDia(d.getDiaSemana()).equals(diaNormalizado))
                .anyMatch(d -> nuevo.getHoraInicio().isBefore(d.getHoraFin())
                        && d.getHoraInicio().isBefore(nuevo.getHoraFin()));
    }

    private String normalizarDia(String dia) {
        if (dia == null) return "";
        return java.text.Normalizer.normalize(dia.trim(), java.text.Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .toUpperCase();
    }
}