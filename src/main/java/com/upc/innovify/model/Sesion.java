package com.upc.innovify.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sesiones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sesion")
    private Integer idSesion;

    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name = "enlace_reunion")
    private String enlaceReunion;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pago_creditos")
    private Integer pagoCreditos;
}