package com.upc.innovify.service;

import com.upc.innovify.model.Notificacion;
import com.upc.innovify.model.Usuario;
import com.upc.innovify.repository.NotificacionRepository;
import com.upc.innovify.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final UsuarioRepository usuarioRepository;
    private final PushNotificationService pushNotificationService;

    public List<Notificacion> getByUsuario(Integer idUsuario) {
        return notificacionRepository.findByIdUsuario(idUsuario);
    }

    public List<Notificacion> getNoLeidas(Integer idUsuario) {
        return notificacionRepository.findByIdUsuarioAndLeido(idUsuario, false);
    }

    public Notificacion create(Notificacion notificacion) {
        notificacion.setLeido(false);

        // Inferir la acción interactiva si no viene especificada
        if (notificacion.getAccion() == null) {
            notificacion.setAccion(inferirAccion(notificacion.getTipo()));
        }

        Notificacion guardada = notificacionRepository.save(notificacion);

        // Disparar a Firebase
        enviarPushSiCorresponde(guardada);

        return guardada;
    }

    public Notificacion marcarLeida(Integer id) {
        Notificacion notificacion = notificacionRepository.findById(id).orElseThrow();
        notificacion.setLeido(true);
        return notificacionRepository.save(notificacion);
    }

    private String inferirAccion(String tipo) {
        if (tipo == null) return null;
        String t = tipo.toLowerCase();
        if (t.contains("mensaje")) return "RESPONDER_MENSAJE";
        if (t.contains("aceptad")) return "IR_A_CHAT";
        return null;
    }

    private void enviarPushSiCorresponde(Notificacion notificacion) {
        try {
            Usuario usuario = usuarioRepository.findById(notificacion.getIdUsuario()).orElse(null);
            if (usuario != null && usuario.getFcmToken() != null && !usuario.getFcmToken().isBlank()) {
                pushNotificationService.enviarPush(usuario.getFcmToken(), notificacion);
            }
        } catch (Exception e) {
            System.err.println("No se pudo enviar push notification: " + e.getMessage());
        }
    }
}