package com.upc.innovify.service;

import com.upc.innovify.model.HistorialCredito;
import com.upc.innovify.repository.HistorialCreditoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialCreditoService {
    private final HistorialCreditoRepository historialCreditoRepository;

    public List<HistorialCredito> getByUsuario(Integer idUsuario) {
        return historialCreditoRepository.findByIdUsuario(idUsuario);
    }

    public HistorialCredito create(HistorialCredito historialCredito) {
        historialCredito.setFecha(LocalDateTime.now());
        return historialCreditoRepository.save(historialCredito);
    }
}