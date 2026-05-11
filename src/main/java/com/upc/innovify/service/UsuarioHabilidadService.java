package com.upc.innovify.service;

import com.upc.innovify.model.UsuarioHabilidad;
import com.upc.innovify.repository.UsuarioHabilidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioHabilidadService {
    private final UsuarioHabilidadRepository usuarioHabilidadRepository;

    public List<UsuarioHabilidad> getAll() {
        return usuarioHabilidadRepository.findAll();
    }

    public List<UsuarioHabilidad> getByUsuario(Integer idUsuario) {
        return usuarioHabilidadRepository.findByIdUsuario(idUsuario);
    }

    public UsuarioHabilidad create(UsuarioHabilidad usuarioHabilidad) {
        return usuarioHabilidadRepository.save(usuarioHabilidad);
    }

    public void delete(Integer id) {
        usuarioHabilidadRepository.deleteById(id);
    }
}