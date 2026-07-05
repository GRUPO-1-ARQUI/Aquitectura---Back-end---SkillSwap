package com.upc.innovify.controller;

import com.upc.innovify.model.Usuario;
import com.upc.innovify.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    // GET /api/usuarios — listar todos los usuarios // HU04 HU05
    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.getAll();
    }

    // GET /api/usuarios/{id} — obtener usuario por ID // HU16
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id) {
        return usuarioService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/usuarios — crear nuevo usuario
    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.create(usuario);
    }

    // PUT /api/usuarios/{id} — actualizar datos del usuario
    @PutMapping("/{id}")
    public Usuario update(@PathVariable Integer id,
                          @RequestBody Usuario usuario) {
        return usuarioService.update(id, usuario);
    }

    // PUT /api/usuarios/{id}/biografia — actualizar biografía del usuario
    @PutMapping("/{id}/biografia")
    public Usuario actualizarBiografia(
            @PathVariable Integer id,
            @RequestBody String biografia) {
        return usuarioService.actualizarBiografia(id, biografia);
    }

    // POST /api/usuarios/login — autenticar con correoInstitucional + password
    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {
        return usuarioService.login(
                usuario.getCorreoInstitucional(),
                usuario.getPassword()
        );
    }

    // DELETE /api/usuarios/{id} — eliminar usuario
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        usuarioService.delete(id);
    }

    // PUT /api/usuarios/{id}/creditos?puntos= — sumar/restar créditos
    @PutMapping("/{id}/creditos")
    public Usuario actualizarCreditos(
            @PathVariable Integer id,
            @RequestParam Integer puntos) {
        return usuarioService.actualizarCreditos(id, puntos);
    }

    // PUT /api/usuarios/{id}/tutor — promover usuario a rol tutor
    @PutMapping("/{id}/tutor")
    public Usuario registrarComoTutor(@PathVariable Integer id) {
        return usuarioService.registrarComoTutor(id);
    }

    // US25 - Buscar estudiantes por nombre o código
    @GetMapping("/estudiantes/buscar")
    public ResponseEntity<?> buscarEstudiantes(@RequestParam String texto) {

        List<Usuario> estudiantes = usuarioService.buscarEstudiantes(texto);

        if (estudiantes.isEmpty()) {
            return ResponseEntity.ok("No se encontraron resultados para tu búsqueda");
        }

        return ResponseEntity.ok(estudiantes);
    }

    // US46 - Exportar lista de estudiantes
    @GetMapping("/estudiantes/exportar")
    public ResponseEntity<?> exportarEstudiantes() {

        List<Usuario> estudiantes = usuarioService.exportarEstudiantes();

        if (estudiantes.isEmpty()) {
            return ResponseEntity.ok("No hay estudiantes para exportar");
        }

        return ResponseEntity.ok(estudiantes);
    }
    // US49 - Listar estudiantes con paginación
    @GetMapping("/estudiantes/paginado")
    public Page<Usuario> getEstudiantesPaginado(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamano) {

        Pageable pageable = PageRequest.of(pagina, tamano);
        return usuarioService.getEstudiantesPaginado(pageable);
    }
}