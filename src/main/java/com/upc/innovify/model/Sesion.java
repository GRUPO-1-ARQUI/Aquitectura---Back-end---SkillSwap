package com.upc.innovify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "La solicitud es obligatoria")
    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @NotNull(message = "La fecha de fin es obligatoria")
    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name = "enlace_reunion")
    private String enlaceReunion;

    @NotBlank(message = "El estado es obligatorio")
    @Column(name = "estado")
    private String estado;

    @Min(value = 0, message = "El pago de créditos no puede ser negativo")
    @Column(name = "pago_creditos")
    private Integer pagoCreditos;
}
