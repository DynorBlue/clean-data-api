package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.CamionDTO;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Camion;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoCamion;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.CamionServicio;

import java.util.List;
import java.util.stream.Collectors;


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
    public ResponseEntity<CamionDTO> crearCamion(@RequestBody Camion camion) {
        return ResponseEntity.ok(toDTO(camionServicio.crearCamion(camion)));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<List<CamionDTO>> obtenerTodos() {
        return ResponseEntity.ok(camionServicio.obtenerTodosCamiones().stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<CamionDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(toDTO(camionServicio.obtenerCamionPorId(id)));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CamionDTO> actualizar(@PathVariable Integer id, @RequestBody Camion camion) {
        camion.setIdCamion(id);
        return ResponseEntity.ok(toDTO(camionServicio.actualizarCamion(camion)));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CamionDTO> actualizarParcial(@PathVariable Integer id, @RequestBody Camion datos) {
        return ResponseEntity.ok(toDTO(camionServicio.actualizarCamionPorId(id, datos)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        camionServicio.eliminarCamion(id);
    }

    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<List<CamionDTO>> obtenerPorEstado(@PathVariable EstadoCamion estado) {
        return ResponseEntity.ok(camionServicio.obtenerCamionPorEstado(estado).stream()
                .map(this::toDTO)
                .collect(Collectors.toList()));
    }

    private CamionDTO toDTO(Camion camion) {
        return new CamionDTO(
                camion.getIdCamion(),
                camion.getPlacas(),
                camion.getModelo(),
                camion.getCapacidadKg(),
                camion.getCapacidadM3(),
                camion.getEstado()
        );
    }
}
