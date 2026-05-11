package com.upc.innovify.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "habilidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habilidad")
    private Integer idHabilidad;

    @Column(name = "nombre")
    private String nombre;
}