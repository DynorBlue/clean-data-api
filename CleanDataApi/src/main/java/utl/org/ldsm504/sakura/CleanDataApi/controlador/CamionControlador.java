package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Camion;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoCamion;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.CamionServicio;

import java.util.List;


@RestController
@RequestMapping("/api/camiones")
public class CamionControlador {
    private final CamionServicio camionServicio;

    public CamionControlador(CamionServicio camionServicio) {
        this.camionServicio = camionServicio;
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Camion crearCamion(@RequestBody Camion camion) {
        return camionServicio.crearCamion(camion);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public List<Camion> obtenerTodos() {
        return camionServicio.obtenerTodosCamiones();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public Camion obtenerPorId(@PathVariable Integer id) {
        return camionServicio.obtenerCamionPorId(id);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Camion actualizar(@PathVariable Integer id, @RequestBody Camion camion) {
        camion.setIdCamion(id);
        return camionServicio.actualizarCamion(camion);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Camion actualizarParcial(@PathVariable Integer id, @RequestBody Camion datos) {
        return camionServicio.actualizarCamionPorId(id, datos);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        camionServicio.eliminarCamion(id);
    }

    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public List<Camion> obtenerPorEstado(@PathVariable EstadoCamion estado) {
        return camionServicio.obtenerCamionPorEstado(estado);
    }
}
