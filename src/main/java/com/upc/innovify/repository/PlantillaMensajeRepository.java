package com.upc.innovify.repository;

import com.upc.innovify.model.PlantillaMensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantillaMensajeRepository extends JpaRepository<PlantillaMensaje, Integer> {
    List<PlantillaMensaje> findByIdUsuario(Integer idUsuario);
}