package com.upc.innovify.model;

import jakarta.persistence.*;
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

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "leido")
    private Boolean leido;
}