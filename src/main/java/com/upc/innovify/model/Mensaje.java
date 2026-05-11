package com.upc.innovify.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensaje")
    private Integer idMensaje;

    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    @Column(name = "id_usuario_remitente")
    private Integer idUsuarioRemitente;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}