package com.upc.innovify.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "correo_institucional", unique = true)
    private String correoInstitucional;

    @Column(name = "codigo_estudiante", unique = true)
    private String codigoEstudiante;

    @Column(name = "password")
    private String password;

    @Column(name = "id_institucion")
    private Integer idInstitucion;

    @Column(name = "rol")
    private String rol;

    @Column(name = "creditos")
    private Integer creditos;

    @Column(name = "reputacion_promedio")
    private Double reputacionPromedio;

    @Column(name = "biografia")
    private String biografia;

    @Column(name = "estado")
    private String estado;

    @Column(name = "verificado")
    private Boolean verificado;

    @Column(name = "fcm_token")
    private String fcmToken;

    @Column(name = "ultima_conexion")
    private LocalDateTime ultimaConexion;
}