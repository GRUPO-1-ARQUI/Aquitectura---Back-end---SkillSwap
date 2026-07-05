package com.upc.innovify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "El aprendiz es obligatorio")
    @Column(name = "id_aprendiz")
    private Integer idAprendiz;

    @NotNull(message = "El tutor es obligatorio")
    @Column(name = "id_tutor")
    private Integer idTutor;

    @NotNull(message = "La habilidad es obligatoria")
    @Column(name = "id_habilidad")
    private Integer idHabilidad;

    @NotBlank(message = "El mensaje es obligatorio")
    @Size(max = 500, message = "El mensaje no puede exceder 500 caracteres")
    @Column(name = "mensaje")
    private String mensaje;

    @NotBlank(message = "El estado es obligatorio")
    @Column(name = "estado")
    private String estado;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha")
    private LocalDateTime fecha;
}
