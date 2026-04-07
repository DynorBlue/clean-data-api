package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.ColoniaDTO;
import utl.org.ldsm504.sakura.CleanDataApi.dto.RutaColoniaDTO;
import utl.org.ldsm504.sakura.CleanDataApi.dto.RutaDTO;
import utl.org.ldsm504.sakura.CleanDataApi.dto.TipoResiduoDTO;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Colonia;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Ruta;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.RutaColonia;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.TipoResiduo;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.ColoniaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.RutaColoniaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.RutaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.TipoResiduoRepositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RutaColoniaControlador {

    private final RutaColoniaRepositorio rutaColoniaRepositorio;
    private final RutaRepositorio rutaRepositorio;
    private final ColoniaRepositorio coloniaRepositorio;
    private final TipoResiduoRepositorio tipoResiduoRepositorio;

    public RutaColoniaControlador(RutaColoniaRepositorio rutaColoniaRepositorio,
                                  RutaRepositorio rutaRepositorio,
                                  ColoniaRepositorio coloniaRepositorio,
                                  TipoResiduoRepositorio tipoResiduoRepositorio) {
        this.rutaColoniaRepositorio = rutaColoniaRepositorio;
        this.rutaRepositorio = rutaRepositorio;
        this.coloniaRepositorio = coloniaRepositorio;
        this.tipoResiduoRepositorio = tipoResiduoRepositorio;
    }

    @PostMapping("/rutas/{idRuta}/colonias")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public RutaColoniaDTO agregarColoniaARuta(
            @PathVariable Integer idRuta,
            @RequestBody Map<String, Object> body) {
        
        Integer idColonia = (Integer) body.get("idColonia");
        Integer idTipoResiduo = (Integer) body.get("idTipoResiduo");
        
        LocalDate fechaRecoleccion = null;
        if (body.get("fechaRecoleccion") != null && !body.get("fechaRecoleccion").toString().isEmpty()) {
            fechaRecoleccion = LocalDate.parse(body.get("fechaRecoleccion").toString());
        }
        
        Ruta ruta = rutaRepositorio.findById(idRuta)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con id " + idRuta));
        
        Colonia colonia = coloniaRepositorio.findById(idColonia)
                .orElseThrow(() -> new RuntimeException("Colonia no encontrada con id " + idColonia));
        
        boolean exists = rutaColoniaRepositorio.findByRutaIdRuta(idRuta).stream()
                .anyMatch(rc -> rc.getColonia().getIdColonia().equals(idColonia));
        
        if (exists) {
            throw new RuntimeException("La colonia ya está asociada a esta ruta");
        }
        
        TipoResiduo tipoResiduo = null;
        if (idTipoResiduo != null) {
            tipoResiduo = tipoResiduoRepositorio.findById(idTipoResiduo)
                    .orElseThrow(() -> new RuntimeException("TipoResiduo no encontrado con id " + idTipoResiduo));
        }
        
        RutaColonia rutaColonia = new RutaColonia(ruta, colonia);
        rutaColonia.setTipoResiduo(tipoResiduo);
        rutaColonia.setFechaRecoleccion(fechaRecoleccion);
        rutaColonia = rutaColoniaRepositorio.save(rutaColonia);
        
        return toDTO(rutaColonia);
    }

    @DeleteMapping("/rutas/{idRuta}/colonias/{idColonia}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarColoniaDeRuta(
            @PathVariable Integer idRuta,
            @PathVariable Integer idColonia) {
        
        List<RutaColonia> relaciones = rutaColoniaRepositorio.findByRutaIdRuta(idRuta);
        
        RutaColonia relacion = relaciones.stream()
                .filter(rc -> rc.getColonia().getIdColonia().equals(idColonia))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("La colonia no está asociada a esta ruta"));
        
        rutaColoniaRepositorio.delete(relacion);
    }

    @GetMapping("/rutas/{idRuta}/colonias")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public List<RutaColoniaDTO> obtenerColoniasDeRuta(@PathVariable Integer idRuta) {
        return rutaColoniaRepositorio.findByRutaIdRuta(idRuta).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/colonias/{idColonia}/rutas")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR', 'CIUDADANO')")
    public List<RutaColoniaDTO> obtenerRutasDeColonia(@PathVariable Integer idColonia) {
        return rutaColoniaRepositorio.findByColoniaIdColonia(idColonia).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private RutaColoniaDTO toDTO(RutaColonia rutaColonia) {
        RutaDTO rutaDTO = new RutaDTO(
                rutaColonia.getRuta().getIdRuta(),
                rutaColonia.getRuta().getNombre(),
                rutaColonia.getRuta().getDescripcion(),
                rutaColonia.getRuta().getActiva()
        );
        
        ColoniaDTO coloniaDTO = new ColoniaDTO(
                rutaColonia.getColonia().getIdColonia(),
                rutaColonia.getColonia().getNombre(),
                rutaColonia.getColonia().getCodigoPostal(),
                rutaColonia.getColonia().getLatitud() != null ? rutaColonia.getColonia().getLatitud().doubleValue() : null,
                rutaColonia.getColonia().getLongitud() != null ? rutaColonia.getColonia().getLongitud().doubleValue() : null
        );
        
        TipoResiduoDTO tipoResiduoDTO = null;
        if (rutaColonia.getTipoResiduo() != null) {
            tipoResiduoDTO = new TipoResiduoDTO(
                    rutaColonia.getTipoResiduo().getIdTipo(),
                    rutaColonia.getTipoResiduo().getNombre()
            );
        }
        
        return new RutaColoniaDTO(
                rutaColonia.getId(),
                rutaDTO,
                coloniaDTO,
                tipoResiduoDTO,
                rutaColonia.getFechaRecoleccion()
        );
    }
}
