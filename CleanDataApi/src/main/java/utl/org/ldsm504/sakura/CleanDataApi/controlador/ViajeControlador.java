package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.ViajeDTO;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.*;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.*;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.ViajeServicio;

import java.util.List;

@RestController
@RequestMapping("/api/viajes")
public class ViajeControlador {

    private final ViajeServicio viajeServicio;
    private final CamionRepositorio camionRepositorio;
    private final ConductorRepositorio conductorRepositorio;
    private final RutaRepositorio rutaRepositorio;
    private final TipoResiduoRepositorio tipoResiduoRepositorio;

    public ViajeControlador(ViajeServicio viajeServicio,
                           CamionRepositorio camionRepositorio,
                           ConductorRepositorio conductorRepositorio,
                           RutaRepositorio rutaRepositorio,
                           TipoResiduoRepositorio tipoResiduoRepositorio) {
        this.viajeServicio = viajeServicio;
        this.camionRepositorio = camionRepositorio;
        this.conductorRepositorio = conductorRepositorio;
        this.rutaRepositorio = rutaRepositorio;
        this.tipoResiduoRepositorio = tipoResiduoRepositorio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaje crear(@RequestBody ViajeDTO dto) {
        Viaje viaje = new Viaje();
        viaje.setCamion(camionRepositorio.findById(dto.getIdCamion())
                .orElseThrow(() -> new RuntimeException("Camion no encontrado con id " + dto.getIdCamion())));
        viaje.setConductor(conductorRepositorio.findById(dto.getIdConductor())
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado con id " + dto.getIdConductor())));
        viaje.setRuta(rutaRepositorio.findById(dto.getIdRuta())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con id " + dto.getIdRuta())));
        viaje.setTipoResiduo(tipoResiduoRepositorio.findById(dto.getIdTipoResiduo())
                .orElseThrow(() -> new RuntimeException("TipoResiduo no encontrado con id " + dto.getIdTipoResiduo())));
        viaje.setFechaInicio(dto.getFechaInicio());
        viaje.setFechaFin(dto.getFechaFin());
        viaje.setEstado(dto.getEstado());
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
    public ResponseEntity<Viaje> actualizar(@PathVariable Integer id, @RequestBody ViajeDTO dto) {
        Viaje viaje = new Viaje();
        viaje.setCamion(camionRepositorio.findById(dto.getIdCamion())
                .orElseThrow(() -> new RuntimeException("Camion no encontrado con id " + dto.getIdCamion())));
        viaje.setConductor(conductorRepositorio.findById(dto.getIdConductor())
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado con id " + dto.getIdConductor())));
        viaje.setRuta(rutaRepositorio.findById(dto.getIdRuta())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con id " + dto.getIdRuta())));
        viaje.setTipoResiduo(tipoResiduoRepositorio.findById(dto.getIdTipoResiduo())
                .orElseThrow(() -> new RuntimeException("TipoResiduo no encontrado con id " + dto.getIdTipoResiduo())));
        viaje.setFechaInicio(dto.getFechaInicio());
        viaje.setFechaFin(dto.getFechaFin());
        viaje.setEstado(dto.getEstado());
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
