package com.upc.innovify.model;

import jakarta.persistence.*;
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

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_habilidad")
    private Integer idHabilidad;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "nivel")
    private String nivel;
}