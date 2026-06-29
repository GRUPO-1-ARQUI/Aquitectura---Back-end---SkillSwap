package com.upc.innovify.controller;

import com.upc.innovify.dto.LoginRequestDTO;
import com.upc.innovify.dto.LoginResponseDTO;
import com.upc.innovify.dto.UsuarioRequestDTO;
import com.upc.innovify.dto.UsuarioResponseDTO;
import com.upc.innovify.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // GET /api/usuarios
    @GetMapping
    public List<UsuarioResponseDTO> getAll() {
        return usuarioService.getAll();
    }

    // GET /api/usuarios/{id}
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Integer id) {
        return usuarioService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/usuarios
    @PostMapping
    public UsuarioResponseDTO create(@Valid @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.create(dto);
    }

    // PUT /api/usuarios/{id}
    @PutMapping("/{id}")
    public UsuarioResponseDTO update(@PathVariable Integer id,
                                     @Valid @RequestBody UsuarioRequestDTO dto) {
        return usuarioService.update(id, dto);
    }

    // PUT /api/usuarios/{id}/biografia
    @PutMapping("/{id}/biografia")
    public UsuarioResponseDTO actualizarBiografia(
            @PathVariable Integer id,
            @RequestBody String biografia) {
        return usuarioService.actualizarBiografia(id, biografia);
    }

    // POST /api/usuarios/login
    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO dto) {
        return usuarioService.login(dto.getCorreoInstitucional(), dto.getPassword());
    }

    // DELETE /api/usuarios/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        usuarioService.delete(id);
    }

    // PUT /api/usuarios/{id}/creditos?puntos=
    @PreAuthorize("hasRole('COORDINADOR')")
    @PutMapping("/{id}/creditos")
    public UsuarioResponseDTO actualizarCreditos(
            @PathVariable Integer id,
            @RequestParam Integer puntos) {
        return usuarioService.actualizarCreditos(id, puntos);
    }

    // PUT /api/usuarios/{id}/tutor
    @PreAuthorize("hasRole('COORDINADOR')")
    @PutMapping("/{id}/tutor")
    public UsuarioResponseDTO registrarComoTutor(@PathVariable Integer id) {
        return usuarioService.registrarComoTutor(id);
    }
}
