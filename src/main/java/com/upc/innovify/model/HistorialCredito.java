package com.upc.innovify.model;

import jakarta.persistence.*;
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

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "monto")
    private Integer monto;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "fecha")
    private LocalDateTime fecha;
}