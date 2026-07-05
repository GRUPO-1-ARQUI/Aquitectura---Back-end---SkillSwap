package com.upc.innovify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "favoritos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorito")
    private Integer idFavorito;

    @NotNull(message = "El estudiante es obligatorio")
    @Column(name = "id_estudiante")
    private Integer idEstudiante;

    @NotNull(message = "El tutor es obligatorio")
    @Column(name = "id_tutor")
    private Integer idTutor;
}