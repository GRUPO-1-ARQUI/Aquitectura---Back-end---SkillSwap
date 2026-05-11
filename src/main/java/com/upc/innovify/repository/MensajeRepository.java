package com.upc.innovify.repository;

import com.upc.innovify.model.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    List<Mensaje> findByIdSolicitud(Integer idSolicitud);
}