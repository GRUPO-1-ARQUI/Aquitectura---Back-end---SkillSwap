package com.upc.innovify.model;

import jakarta.persistence.*;
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

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_coordinador")
    private Integer idCoordinador;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}