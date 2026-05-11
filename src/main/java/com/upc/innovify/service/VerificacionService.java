package com.upc.innovify.service;

import com.upc.innovify.model.Usuario;
import com.upc.innovify.model.Verificacion;
import com.upc.innovify.repository.UsuarioRepository;
import com.upc.innovify.repository.VerificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VerificacionService {

    private final VerificacionRepository verificacionRepository;
    private final UsuarioRepository usuarioRepository;

    public List<Verificacion> getPendientes() {
        return verificacionRepository.findByEstado("pendiente");
    }

    public Verificacion create(Verificacion verificacion) {
        verificacion.setEstado("pendiente");
        verificacion.setFecha(LocalDateTime.now());

        return verificacionRepository.save(verificacion);
    }

    public Verificacion updateEstado(Integer id, String estado) {

        Verificacion verificacion = verificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Verificación no encontrada"));

        verificacion.setEstado(estado);

        return verificacionRepository.save(verificacion);
    }

    public List<Map<String, Object>> getEstudiantesVerificacion() {

        List<Verificacion> lista = verificacionRepository.findByEstado("pendiente");

        return lista.stream().map(v -> {

            Usuario estudiante = usuarioRepository.findById(v.getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            Map<String, Object> data = new HashMap<>();

            // 🔹 datos verificación
            data.put("idVerificacion", v.getIdVerificacion());
            data.put("estado", v.getEstado());
            data.put("fecha", v.getFecha());

            // 🔹 datos estudiante (US23)
            data.put("idUsuario", estudiante.getIdUsuario());
            data.put("nombre", estudiante.getNombreCompleto());
            data.put("email", estudiante.getCorreoInstitucional());
            data.put("creditos", estudiante.getCreditos());
            data.put("reputacion", estudiante.getReputacionPromedio());

            return data;

        }).toList();
    }
}