package com.upc.innovify.service;

import com.upc.innovify.model.Mensaje;
import com.upc.innovify.repository.MensajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MensajeService {
    private final MensajeRepository mensajeRepository;

    public List<Mensaje> getBySolicitud(Integer idSolicitud) {
        return mensajeRepository.findByIdSolicitud(idSolicitud);
    }

    public Mensaje create(Mensaje mensaje) {
        mensaje.setFecha(LocalDateTime.now());
        return mensajeRepository.save(mensaje);
    }
}