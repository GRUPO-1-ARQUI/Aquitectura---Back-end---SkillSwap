package com.upc.innovify.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "instituciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Institucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_institucion")
    private Integer idInstitucion;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "siglas")
    private String siglas;

    @Column(name = "dominio_correo")
    private String dominioCorreo;
}