package com.upc.innovify.service;

import com.upc.innovify.dto.HistorialSesionDTO;
import com.upc.innovify.model.Sesion;

import java.util.List;
import java.util.Optional;

public interface SesionService {
    List<Sesion> getAll();
    List<HistorialSesionDTO> getHistorialPorTutor(Integer idTutor);
    Optional<Sesion> getById(Integer id);
    List<Sesion> getBySolicitud(Integer idSolicitud);
    Sesion create(Sesion sesion);
    Sesion updateEstado(Integer id, String estado);
}