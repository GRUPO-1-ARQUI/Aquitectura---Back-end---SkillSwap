package com.upc.innovify.service;

import com.upc.innovify.model.Solicitud;
import java.util.List;
import java.util.Optional;

public interface SolicitudService {
    List<Solicitud> getAll();
    Optional<Solicitud> getById(Integer id);
    List<Solicitud> getByTutor(Integer idTutor);
    List<Solicitud> getByAprendiz(Integer idAprendiz);
    Solicitud create(Solicitud solicitud);
    Solicitud updateEstado(Integer id, String estado);
    void delete(Integer id);
}