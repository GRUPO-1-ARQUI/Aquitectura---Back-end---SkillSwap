package com.upc.innovify.repository;

import com.upc.innovify.model.Disponibilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Integer> {
    List<Disponibilidad> findByIdUsuario(Integer idUsuario);
}