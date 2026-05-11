package com.upc.innovify.service;

import com.upc.innovify.model.Resena;
import com.upc.innovify.repository.ResenaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResenaService {

    private final ResenaRepository resenaRepository;

    public List<Resena> getByEvaluado(Integer idUsuarioEvaluado) {
        return resenaRepository.findByIdUsuarioEvaluado(idUsuarioEvaluado);
    }

    public Resena create(Resena resena) {
        return resenaRepository.save(resena);
    }

    public Double getPromedioPorUsuario(Integer idUsuarioEvaluado) {
        List<Resena> resenas = resenaRepository.findByIdUsuarioEvaluado(idUsuarioEvaluado);

        if (resenas.isEmpty()) return 0.0;

        return resenas.stream()
                .mapToInt(Resena::getCalificacion)
                .average()
                .orElse(0.0);
    }
}