package com.upc.innovify.model;

import jakarta.persistence.*;
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

    @Column(name = "id_sesion")
    private Integer idSesion;

    @Column(name = "id_usuario_evaluador")
    private Integer idUsuarioEvaluador;

    @Column(name = "id_usuario_evaluado")
    private Integer idUsuarioEvaluado;

    @Column(name = "calificacion")
    private Integer calificacion;

    @Column(name = "comentario")
    private String comentario;
}