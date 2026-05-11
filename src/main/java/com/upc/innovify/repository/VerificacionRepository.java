package com.upc.innovify.repository;

import com.upc.innovify.model.Verificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VerificacionRepository extends JpaRepository<Verificacion, Integer> {
    List<Verificacion> findByEstado(String estado);
}