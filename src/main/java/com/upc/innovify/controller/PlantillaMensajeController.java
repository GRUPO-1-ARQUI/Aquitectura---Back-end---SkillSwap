package com.upc.innovify.controller;

import com.upc.innovify.model.PlantillaMensaje;
import com.upc.innovify.service.PlantillaMensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plantillas")
@RequiredArgsConstructor
public class PlantillaMensajeController {

    private final PlantillaMensajeService plantillaMensajeService;

    // US36 - Listar plantillas de un usuario/tutor
    @GetMapping("/usuario/{idUsuario}")
    public List<PlantillaMensaje> getByUsuario(@PathVariable Integer idUsuario) {
        return plantillaMensajeService.getByUsuario(idUsuario);
    }

    // US36 - Crear plantilla
    @PostMapping
    public PlantillaMensaje create(@RequestBody PlantillaMensaje plantilla) {
        return plantillaMensajeService.create(plantilla);
    }

    // US36 - Editar plantilla
    @PutMapping("/{id}")
    public PlantillaMensaje update(@PathVariable Integer id,
                                   @RequestBody PlantillaMensaje plantilla) {
        return plantillaMensajeService.update(id, plantilla);
    }

    // US36 - Eliminar plantilla
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        plantillaMensajeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}