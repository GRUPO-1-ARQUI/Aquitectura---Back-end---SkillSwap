package com.upc.innovify.service;

import com.upc.innovify.model.Usuario;
import com.upc.innovify.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getById(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Integer id, Usuario usuario) {

        Usuario existing = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existing.setNombreCompleto(usuario.getNombreCompleto());
        existing.setCorreoInstitucional(usuario.getCorreoInstitucional());
        existing.setCodigoEstudiante(usuario.getCodigoEstudiante());
        existing.setPassword(usuario.getPassword());
        existing.setIdInstitucion(usuario.getIdInstitucion());
        existing.setRol(usuario.getRol());
        existing.setCreditos(usuario.getCreditos());
        existing.setReputacionPromedio(usuario.getReputacionPromedio());
        existing.setBiografia(usuario.getBiografia());
        existing.setEstado(usuario.getEstado());
        existing.setVerificado(usuario.getVerificado());

        return usuarioRepository.save(existing);
    }

    public Usuario actualizarBiografia(Integer id, String biografia) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setBiografia(biografia);

        return usuarioRepository.save(usuario);
    }

    public Usuario login(String correo, String password) {

        Usuario usuario = usuarioRepository.findByCorreoInstitucional(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuario;
    }

    public void delete(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario actualizarCreditos(Integer idUsuario, Integer puntos) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setCreditos(usuario.getCreditos() + puntos);

        return usuarioRepository.save(usuario);
    }

    public Usuario registrarComoTutor(Integer idUsuario) {

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setRol("tutor");

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarTutoresPorHabilidad(String habilidad) {
        return usuarioRepository.findByRol("tutor");
    }

    // US25 - Buscar estudiantes por nombre o código
    public List<Usuario> buscarEstudiantes(String texto) {
        return usuarioRepository.buscarEstudiantes(texto);
    }

    // US46 - Exportar lista de estudiantes
    public List<Usuario> exportarEstudiantes() {
        return usuarioRepository.findByRol("estudiante");
    }
    // US49 - Listar estudiantes con paginación
    public Page<Usuario> getEstudiantesPaginado(Pageable pageable) {
        return usuarioRepository.findByRol("estudiante", pageable);
    }
}