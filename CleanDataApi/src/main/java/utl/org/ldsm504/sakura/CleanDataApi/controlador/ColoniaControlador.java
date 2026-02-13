package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Colonia;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.ColoniaServicio;

import java.util.List;

@RestController
@RequestMapping("/api/colonias")
public class ColoniaControlador {

    private final ColoniaServicio coloniaServicio;

    public ColoniaControlador(ColoniaServicio coloniaServicio) {
        this.coloniaServicio = coloniaServicio;
    }

    // Crear colonia
    @PostMapping
    public ResponseEntity<Colonia> crear(@RequestBody Colonia colonia) {
        return ResponseEntity.ok(coloniaServicio.crearColonia(colonia));
    }

    // Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<Colonia> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(coloniaServicio.obtenerColoniaPorId(id));
    }

    // Listar todas
    @GetMapping
    public ResponseEntity<List<Colonia>> listarTodas() {
        return ResponseEntity.ok(coloniaServicio.obtenerTodasColonia());
    }

    // Actualizar completo
    @PutMapping
    public ResponseEntity<Colonia> actualizar(@RequestBody Colonia colonia) {
        return ResponseEntity.ok(coloniaServicio.actualizarColonia(colonia));
    }

    // Actualizar parcial por id
    @PatchMapping("/{id}")
    public ResponseEntity<Colonia> actualizarPorId(
            @PathVariable Integer id,
            @RequestBody Colonia datos
    ) {
        return ResponseEntity.ok(coloniaServicio.actualizarColoniaPorId(id, datos));
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        coloniaServicio.eliminarColonia(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por nombre exacto
    @GetMapping("/buscar")
    public ResponseEntity<Colonia> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(coloniaServicio.buscarPorNombreExacto(nombre));
    }

    // Buscar por nombre que contenga
    @GetMapping("/buscar/contiene")
    public ResponseEntity<List<Colonia>> buscarContiene(@RequestParam String q) {
        return ResponseEntity.ok(coloniaServicio.buscarPorNombreContiene(q));
    }
}