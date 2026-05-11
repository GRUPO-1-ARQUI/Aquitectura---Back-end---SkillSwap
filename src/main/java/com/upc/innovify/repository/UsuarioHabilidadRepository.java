package com.upc.innovify.repository;

import com.upc.innovify.model.UsuarioHabilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioHabilidadRepository extends JpaRepository<UsuarioHabilidad, Integer> {
    List<UsuarioHabilidad> findByIdUsuario(Integer idUsuario);
}