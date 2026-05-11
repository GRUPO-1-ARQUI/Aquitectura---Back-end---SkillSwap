package com.upc.innovify.service;

import com.upc.innovify.model.Notificacion;
import com.upc.innovify.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionService {
    private final NotificacionRepository notificacionRepository;

    public List<Notificacion> getByUsuario(Integer idUsuario) {
        return notificacionRepository.findByIdUsuario(idUsuario);
    }

    public List<Notificacion> getNoLeidas(Integer idUsuario) {
        return notificacionRepository.findByIdUsuarioAndLeido(idUsuario, false);
    }

    public Notificacion create(Notificacion notificacion) {
        notificacion.setLeido(false);
        return notificacionRepository.save(notificacion);
    }

    public Notificacion marcarLeida(Integer id) {
        Notificacion notificacion = notificacionRepository.findById(id).orElseThrow();
        notificacion.setLeido(true);
        return notificacionRepository.save(notificacion);
    }
}