package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.ResponseEntity;
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

    // Crear (registrar)
    @PostMapping
    public ResponseEntity<TipoResiduo> crear(@RequestBody TipoResiduo tipoResiduo) {
        return ResponseEntity.ok(tipoResiduoServicio.crearTipoResiduo(tipoResiduo));
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoResiduo> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoResiduoServicio.obtenerTipoResiduoPorId(id));
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<TipoResiduo>> listarTodos() {
        return ResponseEntity.ok(tipoResiduoServicio.obtenerTodosTiposResiduos());
    }

    // Actualizar completo
    @PutMapping
    public ResponseEntity<TipoResiduo> actualizar(@RequestBody TipoResiduo tipoResiduo) {
        return ResponseEntity.ok(tipoResiduoServicio.actualizarColonia(tipoResiduo));
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tipoResiduoServicio.eliminarTipoResiduo(id);
        return ResponseEntity.noContent().build();
    }
}