package com.upc.innovify.service;

import com.upc.innovify.dto.LoginResponseDTO;
import com.upc.innovify.dto.UsuarioRequestDTO;
import com.upc.innovify.dto.UsuarioResponseDTO;
import com.upc.innovify.model.Usuario;
import com.upc.innovify.repository.UsuarioRepository;
import com.upc.innovify.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public List<UsuarioResponseDTO> getAll() {
        return usuarioRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public Optional<UsuarioResponseDTO> getById(Integer id) {
        return usuarioRepository.findById(id).map(this::toResponse);
    }

    public UsuarioResponseDTO create(UsuarioRequestDTO dto) {
        String rol = (dto.getRol() == null || dto.getRol().isBlank())
                ? "estudiante"
                : dto.getRol().toLowerCase().trim();

        if (!rol.equals("estudiante") && !rol.equals("tutor")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Rol inválido. Solo se permite estudiante o tutor en el registro.");
        }

        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(dto.getNombreCompleto());
        usuario.setCorreoInstitucional(dto.getCorreoInstitucional().toLowerCase().trim());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setIdInstitucion(dto.getIdInstitucion());
        usuario.setRol(rol);
        usuario.setBiografia(dto.getBiografia());
        usuario.setVerificado(false);
        usuario.setCreditos(0);
        usuario.setEstado("activo");
        usuario.setReputacionPromedio(null);
        return toResponse(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDTO update(Integer id, UsuarioRequestDTO dto) {
        Usuario existing = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        existing.setNombreCompleto(dto.getNombreCompleto());
        existing.setCorreoInstitucional(dto.getCorreoInstitucional());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        existing.setIdInstitucion(dto.getIdInstitucion());
        existing.setRol(dto.getRol());
        existing.setBiografia(dto.getBiografia());

        return toResponse(usuarioRepository.save(existing));
    }

    public UsuarioResponseDTO actualizarBiografia(Integer id, String biografia) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setBiografia(biografia);
        return toResponse(usuarioRepository.save(usuario));
    }

    public LoginResponseDTO login(String correo, String password) {
        String normalizedCorreo = correo.toLowerCase().trim();

        Usuario usuario = usuarioRepository.findByCorreoInstitucional(normalizedCorreo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(jwtTokenProvider.generarToken(usuario));
        response.setIdUsuario(usuario.getIdUsuario());
        response.setNombreCompleto(usuario.getNombreCompleto());
        response.setCorreoInstitucional(usuario.getCorreoInstitucional());
        response.setRol(usuario.getRol());
        return response;
    }

    public void delete(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioResponseDTO actualizarCreditos(Integer idUsuario, Integer puntos) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setCreditos(usuario.getCreditos() + puntos);
        return toResponse(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDTO registrarComoTutor(Integer idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setRol("tutor");
        return toResponse(usuarioRepository.save(usuario));
    }

    public List<UsuarioResponseDTO> buscarTutoresPorHabilidad(String habilidad) {
        return usuarioRepository.findByRol("tutor").stream()
                .map(this::toResponse)
                .toList();
    }

    public List<UsuarioResponseDTO> buscarEstudiantes(String texto) {
        return usuarioRepository.buscarEstudiantes(texto).stream()
                .map(this::toResponse)
                .toList();
    }

    public List<UsuarioResponseDTO> exportarEstudiantes() {
        return usuarioRepository.findByRol("estudiante").stream()
                .map(this::toResponse)
                .toList();
    }

    public Page<UsuarioResponseDTO> getEstudiantesPaginado(Pageable pageable) {
        return usuarioRepository.findByRol("estudiante", pageable)
                .map(this::toResponse);
    }

    private UsuarioResponseDTO toResponse(Usuario u) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setIdUsuario(u.getIdUsuario());
        dto.setNombreCompleto(u.getNombreCompleto());
        dto.setCorreoInstitucional(u.getCorreoInstitucional());
        dto.setIdInstitucion(u.getIdInstitucion());
        dto.setRol(u.getRol());
        dto.setCreditos(u.getCreditos());
        dto.setReputacionPromedio(u.getReputacionPromedio());
        dto.setBiografia(u.getBiografia());
        dto.setEstado(u.getEstado());
        dto.setVerificado(u.getVerificado());
        return dto;
    }
}