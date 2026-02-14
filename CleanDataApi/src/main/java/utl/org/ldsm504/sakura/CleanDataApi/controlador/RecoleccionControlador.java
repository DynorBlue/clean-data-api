package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.RecoleccionDTO;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Recoleccion;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.*;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.RecoleccionServicio;

import java.util.List;

@RestController
@RequestMapping("/api/recolecciones")
public class RecoleccionControlador {

    private final RecoleccionServicio recoleccionServicio;
    private final ViajeRepositorio viajeRepositorio;
    private final TipoResiduoRepositorio tipoResiduoRepositorio;

    public RecoleccionControlador(RecoleccionServicio recoleccionServicio,
                                   ViajeRepositorio viajeRepositorio,
                                   TipoResiduoRepositorio tipoResiduoRepositorio) {
        this.recoleccionServicio = recoleccionServicio;
        this.viajeRepositorio = viajeRepositorio;
        this.tipoResiduoRepositorio = tipoResiduoRepositorio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recoleccion crear(@RequestBody RecoleccionDTO dto) {
        Recoleccion recoleccion = new Recoleccion();
        recoleccion.setViaje(viajeRepositorio.findById(dto.getIdViaje())
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado con id " + dto.getIdViaje())));
        recoleccion.setTipoResiduo(tipoResiduoRepositorio.findById(dto.getIdTipoResiduo())
                .orElseThrow(() -> new RuntimeException("TipoResiduo no encontrado con id " + dto.getIdTipoResiduo())));
        recoleccion.setVolumenM3(dto.getVolumenM3());
        recoleccion.setPesoKg(dto.getPesoKg());
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
    public ResponseEntity<Recoleccion> actualizar(@PathVariable Integer id, @RequestBody RecoleccionDTO dto) {
        Recoleccion recoleccion = new Recoleccion();
        recoleccion.setViaje(viajeRepositorio.findById(dto.getIdViaje())
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado con id " + dto.getIdViaje())));
        recoleccion.setTipoResiduo(tipoResiduoRepositorio.findById(dto.getIdTipoResiduo())
                .orElseThrow(() -> new RuntimeException("TipoResiduo no encontrado con id " + dto.getIdTipoResiduo())));
        recoleccion.setVolumenM3(dto.getVolumenM3());
        recoleccion.setPesoKg(dto.getPesoKg());
        recoleccion.setIdRecoleccion(id);
        return ResponseEntity.ok(recoleccionServicio.actualizarRecoleccion(recoleccion));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        recoleccionServicio.eliminarRecoleccion(id);
    }
}
