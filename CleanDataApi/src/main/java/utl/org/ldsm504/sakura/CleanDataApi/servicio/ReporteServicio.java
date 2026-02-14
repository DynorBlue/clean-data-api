package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoReporte;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Reporte;

import java.util.List;

public interface ReporteServicio {
    Reporte crearReporte(Reporte reporte);
    Reporte obtenerReportePorId(Integer id);
    List<Reporte> obtenerTodosReportes();
    Reporte actualizarReporte(Reporte reporte);
    void eliminarReporte(Integer id);
    List<Reporte> obtenerPorEstado(EstadoReporte estado);
    Reporte cambiarEstado(Integer id, EstadoReporte nuevoEstado);
}
