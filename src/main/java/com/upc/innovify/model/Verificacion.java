package com.upc.innovify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "verificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Verificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_verificacion")
    private Integer idVerificacion;

    @NotNull(message = "El usuario es obligatorio")
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_coordinador")
    private Integer idCoordinador;

    @NotBlank(message = "El estado es obligatorio")
    @Column(name = "estado")
    private String estado;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha")
    private LocalDateTime fecha;
}
