package com.upc.innovify.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    @Column(name = "id_aprendiz")
    private Integer idAprendiz;

    @Column(name = "id_tutor")
    private Integer idTutor;

    @Column(name = "id_habilidad")
    private Integer idHabilidad;

    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}