package com.upc.innovify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Integer idNotificacion;

    @NotNull(message = "El usuario es obligatorio")
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @NotBlank(message = "El tipo de notificación es obligatorio")
    @Column(name = "tipo")
    private String tipo;

    @NotBlank(message = "El contenido de la notificación es obligatorio")
    @Column(name = "contenido")
    private String contenido;

    @NotNull(message = "El estado de lectura es obligatorio")
    @Column(name = "leido")
    private Boolean leido;
}
