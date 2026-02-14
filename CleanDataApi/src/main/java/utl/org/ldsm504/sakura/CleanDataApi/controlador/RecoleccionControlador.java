package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Recoleccion;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.RecoleccionServicio;

import java.util.List;

@RestController
@RequestMapping("/api/recolecciones")
public class RecoleccionControlador {

    private final RecoleccionServicio recoleccionServicio;

    public RecoleccionControlador(RecoleccionServicio recoleccionServicio) {
        this.recoleccionServicio = recoleccionServicio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recoleccion crear(@RequestBody Recoleccion recoleccion) {
        return recoleccionServicio.crearRecoleccion(recoleccion);
    }

    @GetMapping
    public List<Recoleccion> obtenerTodos() {
        return recoleccionServicio.obtenerTodasRecolecciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recoleccion> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(recoleccionServicio.obtenerRecoleccionPorId(id));
    }

    @GetMapping("/viaje/{idViaje}")
    public List<Recoleccion> obtenerPorViaje(@PathVariable Integer idViaje) {
        return recoleccionServicio.obtenerPorViaje(idViaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recoleccion> actualizar(@PathVariable Integer id, @RequestBody Recoleccion recoleccion) {
        recoleccion.setIdRecoleccion(id);
        return ResponseEntity.ok(recoleccionServicio.actualizarRecoleccion(recoleccion));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        recoleccionServicio.eliminarRecoleccion(id);
    }
}
