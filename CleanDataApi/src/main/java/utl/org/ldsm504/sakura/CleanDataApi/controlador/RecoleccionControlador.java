package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.RecoleccionDTO;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Recoleccion;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.*;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.RecoleccionServicio;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recolecciones")
public class RecoleccionControlador {

    private final RecoleccionServicio recoleccionServicio;
    private final ViajeRepositorio viajeRepositorio;
    private final TipoResiduoRepositorio tipoResiduoRepositorio;

    public RecoleccionControlador(RecoleccionServicio recoleccionServicio,
                                   ViajeRepositorio viajeRepositorio,
                                   TipoResiduoRepositorio tipoResiduoRepositorio) {
        this.recoleccionServicio = recoleccionServicio;
        this.viajeRepositorio = viajeRepositorio;
        this.tipoResiduoRepositorio = tipoResiduoRepositorio;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RecoleccionDTO> crear(@RequestBody RecoleccionDTO dto) {
        Recoleccion recoleccion = new Recoleccion();
        recoleccion.setViaje(viajeRepositorio.findById(dto.getIdViaje())
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado con id " + dto.getIdViaje())));
        recoleccion.setTipoResiduo(tipoResiduoRepositorio.findById(dto.getIdTipoResiduo())
                .orElseThrow(() -> new RuntimeException("TipoResiduo no encontrado con id " + dto.getIdTipoResiduo())));
        recoleccion.setVolumenM3(dto.getVolumenM3());
        recoleccion.setPesoKg(dto.getPesoKg());
        return ResponseEntity.ok(toDTO(recoleccionServicio.crearRecoleccion(recoleccion)));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RecoleccionDTO>> obtenerTodos() {
        return ResponseEntity.ok(recoleccionServicio.obtenerTodasRecolecciones().stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RecoleccionDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(toDTO(recoleccionServicio.obtenerRecoleccionPorId(id)));
    }

    @GetMapping("/viaje/{idViaje}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<List<RecoleccionDTO>> obtenerPorViaje(@PathVariable Integer idViaje) {
        return ResponseEntity.ok(recoleccionServicio.obtenerPorViaje(idViaje).stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RecoleccionDTO> actualizar(@PathVariable Integer id, @RequestBody RecoleccionDTO dto) {
        Recoleccion recoleccion = new Recoleccion();
        recoleccion.setViaje(viajeRepositorio.findById(dto.getIdViaje())
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado con id " + dto.getIdViaje())));
        recoleccion.setTipoResiduo(tipoResiduoRepositorio.findById(dto.getIdTipoResiduo())
                .orElseThrow(() -> new RuntimeException("TipoResiduo no encontrado con id " + dto.getIdTipoResiduo())));
        recoleccion.setVolumenM3(dto.getVolumenM3());
        recoleccion.setPesoKg(dto.getPesoKg());
        recoleccion.setIdRecoleccion(id);
        return ResponseEntity.ok(toDTO(recoleccionServicio.actualizarRecoleccion(recoleccion)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        recoleccionServicio.eliminarRecoleccion(id);
    }

    private RecoleccionDTO toDTO(Recoleccion recoleccion) {
        RecoleccionDTO dto = new RecoleccionDTO();
        dto.setIdRecoleccion(recoleccion.getIdRecoleccion());
        dto.setIdViaje(recoleccion.getViaje() != null ? recoleccion.getViaje().getIdViaje() : null);
        dto.setIdTipoResiduo(recoleccion.getTipoResiduo() != null ? recoleccion.getTipoResiduo().getIdTipo() : null);
        dto.setVolumenM3(recoleccion.getVolumenM3());
        dto.setPesoKg(recoleccion.getPesoKg());
        dto.setFechaRegistro(recoleccion.getFechaRegistro());
        return dto;
    }
}
