package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
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

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Camion crearCamion(@RequestBody Camion camion) {
        return camionServicio.crearCamion(camion);
    }

    // READ ALL
    @GetMapping
    public List<Camion> obtenerTodos() {
        return camionServicio.obtenerTodosCamiones();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Camion obtenerPorId(@PathVariable Integer id) {
        return camionServicio.obtenerCamionPorId(id);
    }

    // UPDATE COMPLETO (PUT)
    @PutMapping("/{id}")
    public Camion actualizar(@PathVariable Integer id, @RequestBody Camion camion) {
        camion.setIdCamion(id);
        return camionServicio.actualizarCamion(camion);
    }

    // UPDATE PARCIAL (PATCH)
    @PatchMapping("/{id}")
    public Camion actualizarParcial(@PathVariable Integer id, @RequestBody Camion datos) {
        return camionServicio.actualizarCamionPorId(id, datos);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        camionServicio.eliminarCamion(id);
    }

    // FILTRAR POR ESTADO
    @GetMapping("/estado/{estado}")
    public List<Camion> obtenerPorEstado(@PathVariable EstadoCamion estado) {
        return camionServicio.obtenerCamionPorEstado(estado);
    }
}
