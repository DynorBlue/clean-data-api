package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.*;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.*;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.*;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.ViajeServicio;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/viajes")
public class ViajeControlador {

    private final ViajeServicio viajeServicio;
    private final ViajeRepositorio viajeRepositorio;
    private final CamionRepositorio camionRepositorio;
    private final ConductorRepositorio conductorRepositorio;
    private final RutaRepositorio rutaRepositorio;
    private final TipoResiduoRepositorio tipoResiduoRepositorio;

    public ViajeControlador(ViajeServicio viajeServicio,
                           ViajeRepositorio viajeRepositorio,
                           CamionRepositorio camionRepositorio,
                           ConductorRepositorio conductorRepositorio,
                           RutaRepositorio rutaRepositorio,
                           TipoResiduoRepositorio tipoResiduoRepositorio) {
        this.viajeServicio = viajeServicio;
        this.viajeRepositorio = viajeRepositorio;
        this.camionRepositorio = camionRepositorio;
        this.conductorRepositorio = conductorRepositorio;
        this.rutaRepositorio = rutaRepositorio;
        this.tipoResiduoRepositorio = tipoResiduoRepositorio;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public List<ViajeDTORespuesta> obtenerTodos() {
        return viajeServicio.obtenerTodosViajes().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<ViajeDTORespuesta> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(toDTO(viajeServicio.obtenerViajePorId(id)));
    }

    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ViajeDTORespuesta> obtenerPorEstado(@PathVariable EstadoViaje estado) {
        return viajeServicio.obtenerPorEstado(estado).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/conductor/{idConductor}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public List<ViajeDTORespuesta> obtenerPorConductor(@PathVariable Integer idConductor) {
        return viajeServicio.obtenerPorConductor(idConductor).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/camion/{idCamion}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public List<ViajeDTORespuesta> obtenerPorCamion(@PathVariable Integer idCamion) {
        return viajeServicio.obtenerPorCamion(idCamion).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/colonia/{idColonia}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR', 'CIUDADANO')")
    public List<ViajeDTORespuesta> obtenerPorColonia(@PathVariable Integer idColonia) {
        return viajeRepositorio.findByColoniaIdColonia(idColonia).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<Viaje> iniciarViaje(@PathVariable Integer id) {
        return ResponseEntity.ok(viajeServicio.iniciarViaje(id));
    }

    @PostMapping("/{id}/finalizar")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<Viaje> finalizarViaje(@PathVariable Integer id) {
        return ResponseEntity.ok(viajeServicio.finalizarViaje(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        viajeServicio.eliminarViaje(id);
    }

    private ViajeDTORespuesta toDTO(Viaje viaje) {
        CamionDTO camionDTO = null;
        if (viaje.getCamion() != null) {
            camionDTO = new CamionDTO(
                    viaje.getCamion().getIdCamion(),
                    viaje.getCamion().getPlacas(),
                    viaje.getCamion().getModelo(),
                    viaje.getCamion().getCapacidadKg(),
                    viaje.getCamion().getCapacidadM3(),
                    viaje.getCamion().getEstado()
            );
        }

        ConductorDTO conductorDTO = null;
        if (viaje.getConductor() != null) {
            PersonaDTO personaDTO = null;
            if (viaje.getConductor().getPersona() != null) {
                personaDTO = new PersonaDTO(
                        viaje.getConductor().getPersona().getIdPersona(),
                        viaje.getConductor().getPersona().getNombre(),
                        viaje.getConductor().getPersona().getTelefono()
                );
            }
            conductorDTO = new ConductorDTO(
                    viaje.getConductor().getIdPersona(),
                    viaje.getConductor().getLicencia(),
                    viaje.getConductor().getFechaAlta(),
                    viaje.getConductor().getFechaBaja(),
                    viaje.getConductor().getEstadoOperativo(),
                    personaDTO
            );
        }

        RutaDTO rutaDTO = null;
        if (viaje.getRuta() != null) {
            rutaDTO = new RutaDTO(
                    viaje.getRuta().getIdRuta(),
                    viaje.getRuta().getNombre(),
                    viaje.getRuta().getDescripcion(),
                    viaje.getRuta().getActiva()
            );
        }

        TipoResiduoDTO tipoResiduoDTO = null;
        if (viaje.getTipoResiduo() != null) {
            tipoResiduoDTO = new TipoResiduoDTO(
                    viaje.getTipoResiduo().getIdTipo(),
                    viaje.getTipoResiduo().getNombre()
            );
        }

        ViajeDTORespuesta dto = new ViajeDTORespuesta();
        dto.setIdViaje(viaje.getIdViaje());
        dto.setFechaInicio(viaje.getFechaInicio());
        dto.setFechaFin(viaje.getFechaFin());
        dto.setEstado(viaje.getEstado());
        dto.setCamion(camionDTO);
        dto.setConductor(conductorDTO);
        dto.setRuta(rutaDTO);
        dto.setTipoResiduo(tipoResiduoDTO);
        return dto;
    }
}
