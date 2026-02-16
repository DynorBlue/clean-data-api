package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.TipoResiduo;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.TipoResiduoServicio;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-residuo")
public class TipoResiduoControlador {

    private final TipoResiduoServicio tipoResiduoServicio;

    public TipoResiduoControlador(TipoResiduoServicio tipoResiduoServicio) {
        this.tipoResiduoServicio = tipoResiduoServicio;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipoResiduo> crear(@RequestBody TipoResiduo tipoResiduo) {
        return ResponseEntity.ok(tipoResiduoServicio.crearTipoResiduo(tipoResiduo));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO', 'CONDUCTOR')")
    public ResponseEntity<TipoResiduo> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoResiduoServicio.obtenerTipoResiduoPorId(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO', 'CONDUCTOR')")
    public ResponseEntity<List<TipoResiduo>> listarTodos() {
        return ResponseEntity.ok(tipoResiduoServicio.obtenerTodosTiposResiduos());
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO', 'CONDUCTOR')")
    public ResponseEntity<List<TipoResiduo>> buscarPorNombre(@RequestParam String q) {
        return ResponseEntity.ok(tipoResiduoServicio.buscarPorNombre(q));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TipoResiduo> actualizar(@RequestBody TipoResiduo tipoResiduo) {
        return ResponseEntity.ok(tipoResiduoServicio.actualizarColonia(tipoResiduo));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tipoResiduoServicio.eliminarTipoResiduo(id);
        return ResponseEntity.noContent().build();
    }
}