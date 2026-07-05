package com.upc.innovify.repository;

import com.upc.innovify.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
    List<Reporte> findByIdUsuarioReportado(Integer idUsuarioReportado);
    long countByIdUsuarioReportadoAndFechaAfter(Integer idUsuarioReportado, LocalDateTime desde);
}