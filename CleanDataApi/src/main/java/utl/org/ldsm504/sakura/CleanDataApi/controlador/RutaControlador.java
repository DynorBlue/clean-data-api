package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Ruta;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.RutaServicio;

import java.util.List;

@RestController
@RequestMapping("/api/rutas")
public class RutaControlador {

    private final RutaServicio rutaServicio;

    public RutaControlador(RutaServicio rutaServicio) {
        this.rutaServicio = rutaServicio;
    }

    // Crear ruta
    @PostMapping
    public ResponseEntity<Ruta> crear(@RequestBody Ruta ruta) {
        return ResponseEntity.ok(rutaServicio.crearRuta(ruta));
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Ruta> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(rutaServicio.obtenerRutaPorId(id));
    }

    // Listar todas
    @GetMapping
    public ResponseEntity<List<Ruta>> listarTodas() {
        return ResponseEntity.ok(rutaServicio.obtenerTodosRutas());
    }

    // Actualizar ruta
    @PutMapping
    public ResponseEntity<Ruta> actualizar(@RequestBody Ruta ruta) {
        return ResponseEntity.ok(rutaServicio.actualizarRuta(ruta));
    }

    // Eliminar ruta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        rutaServicio.eliminarRuta(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por nombre (contiene)
    @GetMapping("/buscar")
    public ResponseEntity<List<Ruta>> buscarPorNombre(@RequestParam String q) {
        return ResponseEntity.ok(rutaServicio.buscarPorNombreContiene(q));
    }

    // Listar solo rutas activas
    @GetMapping("/activas")
    public ResponseEntity<List<Ruta>> listarActivas() {
        return ResponseEntity.ok(rutaServicio.bucarPorEstadoActivo());
    }
}