package com.upc.innovify.service;

import com.upc.innovify.model.Notificacion;
import com.upc.innovify.model.Reporte;
import com.upc.innovify.model.Usuario;
import com.upc.innovify.repository.ReporteRepository;
import com.upc.innovify.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteService {

    private static final int UMBRAL_REPORTES = 3;
    private static final int DIAS_VENTANA = 30;

    private final ReporteRepository reporteRepository;
    private final UsuarioRepository usuarioRepository;
    private final NotificacionService notificacionService;

    public List<Reporte> getByUsuarioReportado(Integer idUsuarioReportado) {
        return reporteRepository.findByIdUsuarioReportado(idUsuarioReportado);
    }

    public Reporte create(Reporte reporte) {
        Usuario reportado = usuarioRepository.findById(reporte.getIdUsuarioReportado())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuario reportado no encontrado con id: " + reporte.getIdUsuarioReportado()));

        usuarioRepository.findById(reporte.getIdUsuarioReportante())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Usuario reportante no encontrado con id: " + reporte.getIdUsuarioReportante()));

        if (reporte.getIdUsuarioReportado().equals(reporte.getIdUsuarioReportante())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Un usuario no puede reportarse a sí mismo.");
        }

        reporte.setFecha(LocalDateTime.now());
        Reporte guardado = reporteRepository.save(reporte);

        verificarUmbralYAlertar(reportado);

        return guardado;
    }

    private void verificarUmbralYAlertar(Usuario reportado) {
        LocalDateTime desde = LocalDateTime.now().minusDays(DIAS_VENTANA);
        long totalReportesUltimoMes = reporteRepository
                .countByIdUsuarioReportadoAndFechaAfter(reportado.getIdUsuario(), desde);

        if (totalReportesUltimoMes <= UMBRAL_REPORTES) {
            return; // todavía no cruza el umbral, no se alerta
        }

        List<Usuario> coordinadores = usuarioRepository
                .findByIdInstitucionAndRol(reportado.getIdInstitucion(), "coordinador");

        for (Usuario coordinador : coordinadores) {
            Notificacion alerta = new Notificacion();
            alerta.setIdUsuario(coordinador.getIdUsuario());
            alerta.setTipo("alerta_reportes");
            alerta.setContenido(reportado.getNombreCompleto() + " ha recibido "
                    + totalReportesUltimoMes + " reportes por comportamiento inadecuado en el último mes.");
            alerta.setIdReferencia(reportado.getIdUsuario());
            notificacionService.create(alerta);
        }
    }
}