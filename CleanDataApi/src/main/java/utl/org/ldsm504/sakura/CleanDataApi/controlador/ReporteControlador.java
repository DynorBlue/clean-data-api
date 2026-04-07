package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.*;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoReporte;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Reporte;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Usuario;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.*;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.ReporteServicio;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reportes")
public class ReporteControlador {

    private final ReporteServicio reporteServicio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final ColoniaRepositorio coloniaRepositorio;
    private final TipoResiduoRepositorio tipoResiduoRepositorio;

    public ReporteControlador(ReporteServicio reporteServicio,
                              UsuarioRepositorio usuarioRepositorio,
                              ColoniaRepositorio coloniaRepositorio,
                              TipoResiduoRepositorio tipoResiduoRepositorio) {
        this.reporteServicio = reporteServicio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.coloniaRepositorio = coloniaRepositorio;
        this.tipoResiduoRepositorio = tipoResiduoRepositorio;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    @ResponseStatus(HttpStatus.CREATED)
    public ReporteDTORespuesta crear(@RequestBody ReporteDTO dto) {
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
        return toDTO(reporteServicio.crearReporte(reporte));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<ReporteDTORespuesta> obtenerTodos() {
        return reporteServicio.obtenerTodosReportes().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/mis-reportes")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    public List<ReporteDTORespuesta> obtenerMisReportes(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioRepositorio.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return reporteServicio.obtenerTodosReportes().stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        }
        return reporteServicio.obtenerPorUsuario(usuario.getIdUsuario()).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReporteDTORespuesta> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(toDTO(reporteServicio.obtenerReportePorId(id)));
    }

    @GetMapping("/colonia/{idColonia}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ReporteDTORespuesta> obtenerPorColonia(@PathVariable Integer idColonia) {
        return reporteServicio.obtenerPorColonia(idColonia).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ReporteDTORespuesta> obtenerPorEstado(@PathVariable EstadoReporte estado) {
        return reporteServicio.obtenerPorEstado(estado).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReporteDTORespuesta> actualizar(@PathVariable Integer id, @RequestBody ReporteDTO dto) {
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
        return ResponseEntity.ok(toDTO(reporteServicio.actualizarReporte(reporte)));
    }

    @PatchMapping("/{id}/estado/{estado}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReporteDTORespuesta> cambiarEstado(@PathVariable Integer id, @PathVariable EstadoReporte estado) {
        return ResponseEntity.ok(toDTO(reporteServicio.cambiarEstado(id, estado)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        reporteServicio.eliminarReporte(id);
    }

    private ReporteDTORespuesta toDTO(Reporte reporte) {
        UsuarioDTORespuesta usuarioDTO = null;
        if (reporte.getUsuario() != null) {
            usuarioDTO = new UsuarioDTORespuesta(
                    reporte.getUsuario().getIdUsuario(),
                    reporte.getUsuario().getEmail(),
                    reporte.getUsuario().getTipoUsuario()
            );
        }

        ColoniaDTO coloniaDTO = null;
        if (reporte.getColonia() != null) {
            coloniaDTO = new ColoniaDTO(
                    reporte.getColonia().getIdColonia(),
                    reporte.getColonia().getNombre(),
                    reporte.getColonia().getCodigoPostal(),
                    reporte.getColonia().getLatitud() != null ? reporte.getColonia().getLatitud().doubleValue() : null,
                    reporte.getColonia().getLongitud() != null ? reporte.getColonia().getLongitud().doubleValue() : null
            );
        }

        TipoResiduoDTO tipoResiduoDTO = null;
        if (reporte.getTipoResiduo() != null) {
            tipoResiduoDTO = new TipoResiduoDTO(
                    reporte.getTipoResiduo().getIdTipo(),
                    reporte.getTipoResiduo().getNombre()
            );
        }

        ReporteDTORespuesta dto = new ReporteDTORespuesta();
        dto.setIdReporte(reporte.getIdReporte());
        dto.setDescripcion(reporte.getDescripcion());
        dto.setFecha(reporte.getFecha());
        dto.setEstado(reporte.getEstado());
        dto.setUsuario(usuarioDTO);
        dto.setColonia(coloniaDTO);
        dto.setTipoResiduo(tipoResiduoDTO);
        return dto;
    }
}
