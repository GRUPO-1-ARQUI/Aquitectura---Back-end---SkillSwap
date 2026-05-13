package com.upc.innovify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "usuario_habilidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioHabilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "El usuario es obligatorio")
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @NotNull(message = "La habilidad es obligatoria")
    @Column(name = "id_habilidad")
    private Integer idHabilidad;

    @NotBlank(message = "El tipo es obligatorio")
    @Column(name = "tipo")
    private String tipo;

    @NotBlank(message = "El nivel es obligatorio")
    @Column(name = "nivel")
    private String nivel;
}
