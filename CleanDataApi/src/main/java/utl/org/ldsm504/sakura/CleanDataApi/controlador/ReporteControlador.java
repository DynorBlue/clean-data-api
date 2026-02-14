package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoReporte;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Reporte;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.ReporteServicio;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteControlador {

    private final ReporteServicio reporteServicio;

    public ReporteControlador(ReporteServicio reporteServicio) {
        this.reporteServicio = reporteServicio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reporte crear(@RequestBody Reporte reporte) {
        return reporteServicio.crearReporte(reporte);
    }

    @GetMapping
    public List<Reporte> obtenerTodos() {
        return reporteServicio.obtenerTodosReportes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(reporteServicio.obtenerReportePorId(id));
    }

    @GetMapping("/estado/{estado}")
    public List<Reporte> obtenerPorEstado(@PathVariable EstadoReporte estado) {
        return reporteServicio.obtenerPorEstado(estado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> actualizar(@PathVariable Integer id, @RequestBody Reporte reporte) {
        reporte.setIdReporte(id);
        return ResponseEntity.ok(reporteServicio.actualizarReporte(reporte));
    }

    @PatchMapping("/{id}/estado/{estado}")
    public ResponseEntity<Reporte> cambiarEstado(@PathVariable Integer id, @PathVariable EstadoReporte estado) {
        return ResponseEntity.ok(reporteServicio.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        reporteServicio.eliminarReporte(id);
    }
}
