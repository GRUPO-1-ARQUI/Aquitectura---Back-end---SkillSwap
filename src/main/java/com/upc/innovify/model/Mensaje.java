package com.upc.innovify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "La solicitud es obligatoria")
    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    @NotNull(message = "El remitente es obligatorio")
    @Column(name = "id_usuario_remitente")
    private Integer idUsuarioRemitente;

    @NotBlank(message = "El contenido del mensaje es obligatorio")
    @Size(max = 1000, message = "El mensaje no puede exceder 1000 caracteres")
    @Column(name = "contenido")
    private String contenido;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha")
    private LocalDateTime fecha;
}
