package com.upc.innovify.controller;

import com.upc.innovify.model.Verificacion;
import com.upc.innovify.service.VerificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/verificaciones")
@RequiredArgsConstructor
public class VerificacionController {

    private final VerificacionService verificacionService;

    @GetMapping("/pendientes")
    public ResponseEntity<List<Verificacion>> getPendientes() {
        return ResponseEntity.ok(verificacionService.getPendientes());
    }

    @PostMapping
    public ResponseEntity<Verificacion> create(@Valid @RequestBody Verificacion verificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(verificacionService.create(verificacion));
    }

    @GetMapping("/estudiantes-verificacion/{idUsuario}")
    public ResponseEntity<Map<String, Object>> getEstudiante(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(verificacionService.getEstudiante(idUsuario));
    }

    @PutMapping("/{id}/aprobar")
    public ResponseEntity<Verificacion> aprobar(@PathVariable Integer id) {
        return ResponseEntity.ok(verificacionService.aprobar(id));
    }

    @PutMapping("/{id}/rechazar")
    public ResponseEntity<Verificacion> rechazar(@PathVariable Integer id) {
        return ResponseEntity.ok(verificacionService.rechazar(id));
    }
}
