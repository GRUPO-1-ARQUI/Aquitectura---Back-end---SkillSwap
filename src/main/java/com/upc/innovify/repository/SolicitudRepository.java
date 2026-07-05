package com.upc.innovify.repository;

import com.upc.innovify.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
    List<Solicitud> findByIdTutor(Integer idTutor);
    List<Solicitud> findByIdAprendiz(Integer idAprendiz);

}