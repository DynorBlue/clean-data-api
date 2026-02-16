package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Colonia> crear(@RequestBody Colonia colonia) {
        return ResponseEntity.ok(coloniaServicio.crearColonia(colonia));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    public ResponseEntity<Colonia> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(coloniaServicio.obtenerColoniaPorId(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    public ResponseEntity<List<Colonia>> listarTodas() {
        return ResponseEntity.ok(coloniaServicio.obtenerTodasColonia());
    }

    @GetMapping("/cp/{cp}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    public ResponseEntity<List<Colonia>> buscarPorCodigoPostal(@PathVariable String cp) {
        return ResponseEntity.ok(coloniaServicio.buscarPorCodigoPostal(cp));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Colonia> actualizar(@RequestBody Colonia colonia) {
        return ResponseEntity.ok(coloniaServicio.actualizarColonia(colonia));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Colonia> actualizarPorId(
            @PathVariable Integer id,
            @RequestBody Colonia datos
    ) {
        return ResponseEntity.ok(coloniaServicio.actualizarColoniaPorId(id, datos));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        coloniaServicio.eliminarColonia(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    public ResponseEntity<Colonia> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(coloniaServicio.buscarPorNombreExacto(nombre));
    }

    @GetMapping("/buscar/contiene")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    public ResponseEntity<List<Colonia>> buscarContiene(@RequestParam String q) {
        return ResponseEntity.ok(coloniaServicio.buscarPorNombreContiene(q));
    }
}