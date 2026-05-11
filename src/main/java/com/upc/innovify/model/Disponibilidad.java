package com.upc.innovify.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Entity
@Table(name = "disponibilidad")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disponibilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_disponibilidad")
    private Integer idDisponibilidad;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "dia_semana")
    private String diaSemana;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;
}