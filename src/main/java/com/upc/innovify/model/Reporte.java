package com.upc.innovify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reporte")
    private Integer idReporte;

    @NotNull(message = "El usuario reportado es obligatorio")
    @Column(name = "id_usuario_reportado")
    private Integer idUsuarioReportado;

    @NotNull(message = "El usuario reportante es obligatorio")
    @Column(name = "id_usuario_reportante")
    private Integer idUsuarioReportante;

    @NotBlank(message = "El motivo del reporte es obligatorio")
    @Size(max = 500, message = "El motivo no puede exceder 500 caracteres")
    @Column(name = "motivo")
    private String motivo;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}