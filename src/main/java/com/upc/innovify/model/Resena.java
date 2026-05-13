package com.upc.innovify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "resenas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resena")
    private Integer idResena;

    @NotNull(message = "La sesión es obligatoria")
    @Column(name = "id_sesion")
    private Integer idSesion;

    @NotNull(message = "El evaluador es obligatorio")
    @Column(name = "id_usuario_evaluador")
    private Integer idUsuarioEvaluador;

    @NotNull(message = "El evaluado es obligatorio")
    @Column(name = "id_usuario_evaluado")
    private Integer idUsuarioEvaluado;

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    @Column(name = "calificacion")
    private Integer calificacion;

    @Size(max = 500, message = "El comentario no puede exceder 500 caracteres")
    @Column(name = "comentario")
    private String comentario;
}
