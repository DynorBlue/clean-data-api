package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.TipoResiduoDTO;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.TipoResiduo;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.TipoResiduoServicio;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tipos-residuo")
public class TipoResiduoControlador {

    private final TipoResiduoServicio tipoResiduoServicio;

    public TipoResiduoControlador(TipoResiduoServicio tipoResiduoServicio) {
        this.tipoResiduoServicio = tipoResiduoServicio;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipoResiduoDTO> crear(@RequestBody TipoResiduo tipoResiduo) {
        return ResponseEntity.ok(toDTO(tipoResiduoServicio.crearTipoResiduo(tipoResiduo)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO', 'CONDUCTOR')")
    public ResponseEntity<TipoResiduoDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(toDTO(tipoResiduoServicio.obtenerTipoResiduoPorId(id)));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO', 'CONDUCTOR')")
    public ResponseEntity<List<TipoResiduoDTO>> listarTodos() {
        return ResponseEntity.ok(tipoResiduoServicio.obtenerTodosTiposResiduos().stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO', 'CONDUCTOR')")
    public ResponseEntity<List<TipoResiduoDTO>> buscarPorNombre(@RequestParam String q) {
        return ResponseEntity.ok(tipoResiduoServicio.buscarPorNombre(q).stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipoResiduoDTO> actualizar(@PathVariable Integer id, @RequestBody TipoResiduo tipoResiduo) {
        tipoResiduo.setIdTipo(id);
        return ResponseEntity.ok(toDTO(tipoResiduoServicio.actualizarTipoResiduo(tipoResiduo)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tipoResiduoServicio.eliminarTipoResiduo(id);
        return ResponseEntity.noContent().build();
    }

    private TipoResiduoDTO toDTO(TipoResiduo tipoResiduo) {
        return new TipoResiduoDTO(
                tipoResiduo.getIdTipo(),
                tipoResiduo.getNombre()
        );
    }
}
