package com.upc.innovify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

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

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @NotBlank(message = "El correo institucional es obligatorio")
    @Email(message = "El formato del correo es inválido")
    @Column(name = "correo_institucional", unique = true)
    private String correoInstitucional;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Column(name = "password")
    private String password;

    @NotNull(message = "La institución es obligatoria")
    @Column(name = "id_institucion")
    private Integer idInstitucion;

    @NotBlank(message = "El rol es obligatorio")
    @Column(name = "rol")
    private String rol;

    @Column(name = "creditos")
    private Integer creditos;

    @Column(name = "reputacion_promedio")
    private Double reputacionPromedio;

    @Size(max = 500, message = "La biografía no puede exceder 500 caracteres")
    @Column(name = "biografia")
    private String biografia;

    @Column(name = "estado")
    private String estado;

    @Column(name = "verificado")
    private Boolean verificado;
}
