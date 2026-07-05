package com.upc.innovify.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.upc.innovify.model.Notificacion;
import org.springframework.stereotype.Service;

@Service
public class PushNotificationService {

    public void enviarPush(String token, Notificacion notificacion) throws FirebaseMessagingException {
        // 1. Arma lo que el usuario ve en la pantalla (Título y cuerpo)
        Notification contenidoPush = Notification.builder()
                .setTitle(tituloSegunTipo(notificacion.getTipo()))
                .setBody(notificacion.getContenido())
                .build();

        // 2. Arma el mensaje completo con la "data" oculta para los botones interactivos
        Message.Builder builder = Message.builder()
                .setToken(token)
                .setNotification(contenidoPush)
                .putData("idNotificacion", String.valueOf(notificacion.getIdNotificacion()))
                .putData("tipo", notificacion.getTipo() != null ? notificacion.getTipo() : "")
                .putData("accion", notificacion.getAccion() != null ? notificacion.getAccion() : "");

        if (notificacion.getIdReferencia() != null) {
            builder.putData("idReferencia", String.valueOf(notificacion.getIdReferencia()));
        }

        // 3. Envía el mensaje a Firebase
        FirebaseMessaging.getInstance().send(builder.build());
    }

    private String tituloSegunTipo(String tipo) {
        if (tipo == null) return "SkillSwap";

        return switch (tipo) {
            case "mensaje", "nuevo_mensaje" -> "Nuevo mensaje";
            case "solicitud_aceptada" -> "Solicitud aceptada";
            case "solicitud_creada" -> "Nueva solicitud";
            default -> "SkillSwap";
        };
    }
}