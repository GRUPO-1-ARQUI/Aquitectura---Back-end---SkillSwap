package com.upc.innovify.service;

import com.upc.innovify.model.Verificacion;

import java.util.List;
import java.util.Map;

public interface VerificacionService {

    List<Verificacion> getPendientes();

    Verificacion create(Verificacion verificacion);

    Verificacion aprobar(Integer id);

    Verificacion rechazar(Integer id);

    Map<String, Object> getEstudiante(Integer idUsuario);

    List<Map<String, Object>> getEstudiantesVerificacion();
}
