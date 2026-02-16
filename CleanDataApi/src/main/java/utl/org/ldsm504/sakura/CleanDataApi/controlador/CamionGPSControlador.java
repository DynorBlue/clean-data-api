package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.CamionGPS;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.CamionGPSServicio;

@RestController
@RequestMapping("/api/camiones-gps")
public class CamionGPSControlador {

    private final CamionGPSServicio gpsServicio;

    public CamionGPSControlador(CamionGPSServicio gpsServicio) {
        this.gpsServicio = gpsServicio;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public java.util.List<CamionGPS> obtenerTodos() {
        return gpsServicio.obtenerTodos();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public CamionGPS crear(@RequestBody CamionGPS gps) {
        return gpsServicio.crearOActualizarGPS(gps);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<CamionGPS> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(gpsServicio.obtenerPorId(id));
    }

    @GetMapping("/camion/{idCamion}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<CamionGPS> obtenerPorCamion(@PathVariable Integer idCamion) {
        return ResponseEntity.ok(gpsServicio.obtenerPorCamion(idCamion));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CamionGPS> actualizar(@PathVariable Integer id, @RequestBody CamionGPS gps) {
        gps.setIdCamion(id);
        return ResponseEntity.ok(gpsServicio.crearOActualizarGPS(gps));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        gpsServicio.eliminarGPS(id);
    }
}
