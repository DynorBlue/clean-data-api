package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ruta> crear(@RequestBody Ruta ruta) {
        return ResponseEntity.ok(rutaServicio.crearRuta(ruta));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<Ruta> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(rutaServicio.obtenerRutaPorId(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<List<Ruta>> listarTodas() {
        return ResponseEntity.ok(rutaServicio.obtenerTodosRutas());
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ruta> actualizar(@RequestBody Ruta ruta) {
        return ResponseEntity.ok(rutaServicio.actualizarRuta(ruta));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        rutaServicio.eliminarRuta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<List<Ruta>> buscarPorNombre(@RequestParam String q) {
        return ResponseEntity.ok(rutaServicio.buscarPorNombreContiene(q));
    }

    @GetMapping("/activas")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<List<Ruta>> listarActivas() {
        return ResponseEntity.ok(rutaServicio.bucarPorEstadoActivo());
    }
}