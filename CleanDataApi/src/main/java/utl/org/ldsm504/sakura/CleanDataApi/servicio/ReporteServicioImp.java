package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoReporte;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Reporte;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.ReporteRepositorio;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReporteServicioImp implements ReporteServicio {

    private final ReporteRepositorio reporteRepositorio;

    public ReporteServicioImp(ReporteRepositorio reporteRepositorio) {
        this.reporteRepositorio = reporteRepositorio;
    }

    @Override
    public Reporte crearReporte(Reporte reporte) {
        return reporteRepositorio.save(reporte);
    }

    @Override
    public Reporte obtenerReportePorId(Integer id) {
        return reporteRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con id " + id));
    }

    @Override
    public List<Reporte> obtenerTodosReportes() {
        return reporteRepositorio.findAll();
    }

    @Transactional
    @Override
    public Reporte actualizarReporte(Reporte reporte) {
        if (reporte.getIdReporte() == null) {
            throw new RuntimeException("El reporte no tiene ID");
        }

        Reporte existente = obtenerReportePorId(reporte.getIdReporte());
        existente.setUsuario(reporte.getUsuario());
        existente.setColonia(reporte.getColonia());
        existente.setTipoResiduo(reporte.getTipoResiduo());
        existente.setDescripcion(reporte.getDescripcion());
        existente.setEstado(reporte.getEstado());

        return reporteRepositorio.save(existente);
    }

    @Override
    public void eliminarReporte(Integer id) {
        if (!reporteRepositorio.existsById(id)) {
            throw new RuntimeException("No existe reporte con id " + id);
        }
        reporteRepositorio.deleteById(id);
    }

    @Override
    public List<Reporte> obtenerPorEstado(EstadoReporte estado) {
        return reporteRepositorio.findByEstado(estado);
    }

    @Transactional
    @Override
    public Reporte cambiarEstado(Integer id, EstadoReporte nuevoEstado) {
        Reporte reporte = obtenerReportePorId(id);
        reporte.setEstado(nuevoEstado);
        return reporteRepositorio.save(reporte);
    }
}
