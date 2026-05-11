package com.upc.innovify.repository;

import com.upc.innovify.model.HistorialCredito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistorialCreditoRepository extends JpaRepository<HistorialCredito, Integer> {
    List<HistorialCredito> findByIdUsuario(Integer idUsuario);
}