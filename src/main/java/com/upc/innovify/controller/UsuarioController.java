package com.upc.innovify.controller;

import com.upc.innovify.model.Usuario;
import com.upc.innovify.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id) {
        return usuarioService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.create(usuario);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable Integer id,
                          @RequestBody Usuario usuario) {
        return usuarioService.update(id, usuario);
    }

    @PutMapping("/{id}/biografia")
    public Usuario actualizarBiografia(
            @PathVariable Integer id,
            @RequestBody String biografia) {
        return usuarioService.actualizarBiografia(id, biografia);
    }
// se agrego para las historias de usuario que tengan que ver con coordinador, estudiante o tutor
    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {
        return usuarioService.login(
                usuario.getCorreoInstitucional(),
                usuario.getPassword()
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        usuarioService.delete(id);
    }

    @PutMapping("/{id}/creditos")
    public Usuario actualizarCreditos(
            @PathVariable Integer id,
            @RequestParam Integer puntos) {

        return usuarioService.actualizarCreditos(id, puntos);
    }
    @PutMapping("/{id}/tutor")
    public Usuario registrarComoTutor(@PathVariable Integer id) {
        return usuarioService.registrarComoTutor(id);
    }
}