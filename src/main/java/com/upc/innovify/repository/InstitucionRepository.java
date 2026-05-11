package com.upc.innovify.repository;

import com.upc.innovify.model.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitucionRepository extends JpaRepository<Institucion, Integer> {
}