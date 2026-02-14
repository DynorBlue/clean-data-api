package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CamionGPS crear(@RequestBody CamionGPS gps) {
        return gpsServicio.crearOActualizarGPS(gps);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CamionGPS> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(gpsServicio.obtenerPorId(id));
    }

    @GetMapping("/camion/{idCamion}")
    public ResponseEntity<CamionGPS> obtenerPorCamion(@PathVariable Integer idCamion) {
        return ResponseEntity.ok(gpsServicio.obtenerPorCamion(idCamion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CamionGPS> actualizar(@PathVariable Integer id, @RequestBody CamionGPS gps) {
        gps.setIdCamion(id);
        return ResponseEntity.ok(gpsServicio.crearOActualizarGPS(gps));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        gpsServicio.eliminarGPS(id);
    }
}
