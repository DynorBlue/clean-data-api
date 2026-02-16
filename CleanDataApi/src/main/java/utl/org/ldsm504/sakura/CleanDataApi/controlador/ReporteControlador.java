package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.ReporteDTO;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoReporte;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Reporte;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Usuario;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.*;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.ReporteServicio;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteControlador {

    private final ReporteServicio reporteServicio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final ColoniaRepositorio coloniaRepositorio;
    private final TipoResiduoRepositorio tipoResiduoRepositorio;
    private final UsuarioRepositorio usuarioRepo;

    public ReporteControlador(ReporteServicio reporteServicio,
                              UsuarioRepositorio usuarioRepositorio,
                              ColoniaRepositorio coloniaRepositorio,
                              TipoResiduoRepositorio tipoResiduoRepositorio,
                              UsuarioRepositorio usuarioRepo) {
        this.reporteServicio = reporteServicio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.coloniaRepositorio = coloniaRepositorio;
        this.tipoResiduoRepositorio = tipoResiduoRepositorio;
        this.usuarioRepo = usuarioRepositorio;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    @ResponseStatus(HttpStatus.CREATED)
    public Reporte crear(@RequestBody ReporteDTO dto) {
        Reporte reporte = new Reporte();
        reporte.setUsuario(usuarioRepositorio.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.getIdUsuario())));
        reporte.setColonia(coloniaRepositorio.findById(dto.getIdColonia())
                .orElseThrow(() -> new RuntimeException("Colonia no encontrada con id " + dto.getIdColonia())));
        if (dto.getIdTipoResiduo() != null) {
            reporte.setTipoResiduo(tipoResiduoRepositorio.findById(dto.getIdTipoResiduo())
                    .orElseThrow(() -> new RuntimeException("TipoResiduo no encontrado con id " + dto.getIdTipoResiduo())));
        }
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setEstado(EstadoReporte.PENDIENTE);
        return reporteServicio.crearReporte(reporte);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Reporte> obtenerTodos() {
        return reporteServicio.obtenerTodosReportes();
    }

    @GetMapping("/mis-reportes")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    public List<Reporte> obtenerMisReportes(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioRepo.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return reporteServicio.obtenerTodosReportes();
        }
        return reporteServicio.obtenerPorUsuario(usuario.getIdUsuario());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Reporte> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(reporteServicio.obtenerReportePorId(id));
    }

    @GetMapping("/colonia/{idColonia}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Reporte> obtenerPorColonia(@PathVariable Integer idColonia) {
        return reporteServicio.obtenerPorColonia(idColonia);
    }

    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Reporte> obtenerPorEstado(@PathVariable EstadoReporte estado) {
        return reporteServicio.obtenerPorEstado(estado);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Reporte> actualizar(@PathVariable Integer id, @RequestBody ReporteDTO dto) {
        Reporte reporte = new Reporte();
        reporte.setUsuario(usuarioRepositorio.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.getIdUsuario())));
        reporte.setColonia(coloniaRepositorio.findById(dto.getIdColonia())
                .orElseThrow(() -> new RuntimeException("Colonia no encontrada con id " + dto.getIdColonia())));
        if (dto.getIdTipoResiduo() != null) {
            reporte.setTipoResiduo(tipoResiduoRepositorio.findById(dto.getIdTipoResiduo())
                    .orElseThrow(() -> new RuntimeException("TipoResiduo no encontrado con id " + dto.getIdTipoResiduo())));
        }
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setEstado(dto.getEstado());
        reporte.setIdReporte(id);
        return ResponseEntity.ok(reporteServicio.actualizarReporte(reporte));
    }

    @PatchMapping("/{id}/estado/{estado}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Reporte> cambiarEstado(@PathVariable Integer id, @PathVariable EstadoReporte estado) {
        return ResponseEntity.ok(reporteServicio.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        reporteServicio.eliminarReporte(id);
    }
}
