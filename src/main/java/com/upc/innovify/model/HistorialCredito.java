package com.upc.innovify.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_creditos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialCredito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Integer idTransaccion;

    @NotNull(message = "El usuario es obligatorio")
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @NotNull(message = "El monto es obligatorio")
    @Column(name = "monto")
    private Integer monto;

    @NotBlank(message = "El tipo de transacción es obligatorio")
    @Column(name = "tipo")
    private String tipo;

    @NotBlank(message = "El motivo es obligatorio")
    @Column(name = "motivo")
    private String motivo;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "fecha")
    private LocalDateTime fecha;
}
