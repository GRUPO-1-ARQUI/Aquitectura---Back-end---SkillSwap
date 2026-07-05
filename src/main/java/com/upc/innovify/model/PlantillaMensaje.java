package com.upc.innovify.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "plantilla_mensaje")
@Data
public class PlantillaMensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlantilla;

    private Integer idUsuario;

    private String titulo;

    @Column(length = 1000)
    private String contenido;
}