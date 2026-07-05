package com.upc.innovify.service.impl;

import com.upc.innovify.model.Usuario;
import com.upc.innovify.model.Verificacion;
import com.upc.innovify.repository.UsuarioRepository;
import com.upc.innovify.repository.VerificacionRepository;
import com.upc.innovify.service.VerificacionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VerificacionServiceImpl implements VerificacionService {

    private final VerificacionRepository verificacionRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Verificacion> getPendientes() {
        return verificacionRepository.findByEstado("pendiente");
    }

    @Override
    public Verificacion create(Verificacion verificacion) {
        verificacion.setEstado("pendiente");
        verificacion.setFecha(LocalDateTime.now());
        return verificacionRepository.save(verificacion);
    }

    @Override
    @Transactional
    public Verificacion updateEstado(Integer id, String estado) {
        Verificacion verificacion = verificacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Verificación no encontrada con id: " + id));
        verificacion.setEstado(estado);
        return verificacionRepository.save(verificacion);
    }

    @Override
    @Transactional
    public Verificacion aprobar(Integer id) {
        Verificacion verificacion = verificacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Verificación no encontrada con id: " + id));

        verificacion.setEstado("aprobado");
        Verificacion saved = verificacionRepository.save(verificacion);

        Usuario usuario = usuarioRepository.findById(verificacion.getIdUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuario vinculado no encontrado con id: " + verificacion.getIdUsuario()));

        usuario.setVerificado(true);
        usuarioRepository.save(usuario);

        return saved;
    }

    @Override
    @Transactional
    public Verificacion rechazar(Integer id) {
        Verificacion verificacion = verificacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Verificación no encontrada con id: " + id));

        verificacion.setEstado("rechazado");
        return verificacionRepository.save(verificacion);
    }

    @Override
    public Map<String, Object> getEstudiante(Integer idUsuario) {
        Usuario estudiante = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Estudiante no encontrado con id: " + idUsuario));

        Map<String, Object> response = new HashMap<>();
        response.put("idUsuario", estudiante.getIdUsuario());
        response.put("nombre", estudiante.getNombreCompleto());
        response.put("email", estudiante.getCorreoInstitucional());
        response.put("estado", estudiante.getEstado());
        response.put("verificado", estudiante.getVerificado());
        return response;
    }

    @Override
    public List<Map<String, Object>> getEstudiantesVerificacion() {
        return verificacionRepository.findByEstado("pendiente").stream().map(v -> {
            Usuario estudiante = usuarioRepository.findById(v.getIdUsuario())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Usuario no encontrado con id: " + v.getIdUsuario()));

            Map<String, Object> data = new HashMap<>();
            data.put("idVerificacion", v.getIdVerificacion());
            data.put("estado", v.getEstado());
            data.put("fecha", v.getFecha());
            data.put("idUsuario", estudiante.getIdUsuario());
            data.put("nombre", estudiante.getNombreCompleto());
            data.put("email", estudiante.getCorreoInstitucional());
            data.put("creditos", estudiante.getCreditos());
            data.put("reputacion", estudiante.getReputacionPromedio());
            return data;
        }).toList();
    }
}