package com.upc.innovify.service;

import com.upc.innovify.model.Sesion;

import java.util.List;
import java.util.Optional;

public interface SesionService {

    List<Sesion> getAll();

    Optional<Sesion> getById(Integer id);

    List<Sesion> getBySolicitud(Integer idSolicitud);

    Sesion create(Sesion sesion);

    Sesion updateEstado(Integer id, String estado);
}
