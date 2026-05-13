package com.upc.innovify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "El nombre de la institución es obligatorio")
    @Size(max = 150, message = "El nombre no puede exceder 150 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "Las siglas son obligatorias")
    @Size(max = 20, message = "Las siglas no pueden exceder 20 caracteres")
    @Column(name = "siglas")
    private String siglas;

    @NotBlank(message = "El dominio de correo es obligatorio")
    @Column(name = "dominio_correo")
    private String dominioCorreo;
}
