package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoViaje;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Viaje;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.ViajeServicio;

import java.util.List;

@RestController
@RequestMapping("/api/viajes")
public class ViajeControlador {

    private final ViajeServicio viajeServicio;

    public ViajeControlador(ViajeServicio viajeServicio) {
        this.viajeServicio = viajeServicio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaje crear(@RequestBody Viaje viaje) {
        return viajeServicio.crearViaje(viaje);
    }

    @GetMapping
    public List<Viaje> obtenerTodos() {
        return viajeServicio.obtenerTodosViajes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viaje> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(viajeServicio.obtenerViajePorId(id));
    }

    @GetMapping("/estado/{estado}")
    public List<Viaje> obtenerPorEstado(@PathVariable EstadoViaje estado) {
        return viajeServicio.obtenerPorEstado(estado);
    }

    @GetMapping("/conductor/{idConductor}")
    public List<Viaje> obtenerPorConductor(@PathVariable Integer idConductor) {
        return viajeServicio.obtenerPorConductor(idConductor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaje> actualizar(@PathVariable Integer id, @RequestBody Viaje viaje) {
        viaje.setIdViaje(id);
        return ResponseEntity.ok(viajeServicio.actualizarViaje(viaje));
    }

    @PostMapping("/{id}/iniciar")
    public ResponseEntity<Viaje> iniciarViaje(@PathVariable Integer id) {
        return ResponseEntity.ok(viajeServicio.iniciarViaje(id));
    }

    @PostMapping("/{id}/finalizar")
    public ResponseEntity<Viaje> finalizarViaje(@PathVariable Integer id) {
        return ResponseEntity.ok(viajeServicio.finalizarViaje(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        viajeServicio.eliminarViaje(id);
    }
}
