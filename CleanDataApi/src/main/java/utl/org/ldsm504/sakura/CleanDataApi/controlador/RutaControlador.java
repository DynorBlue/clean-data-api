package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.config.JwtUtil;
import utl.org.ldsm504.sakura.CleanDataApi.dto.ResiduoDiaDTO;
import utl.org.ldsm504.sakura.CleanDataApi.dto.RutaDTO;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Ciudadano;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Ruta;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.RutaColonia;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.CiudadanoRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.RutaColoniaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.RutaServicio;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rutas")
public class RutaControlador {

    private final RutaServicio rutaServicio;
    private final RutaColoniaRepositorio rutaColoniaRepositorio;
    private final CiudadanoRepositorio ciudadanoRepositorio;
    private final JwtUtil jwtUtil;

    public RutaControlador(RutaServicio rutaServicio,
                          RutaColoniaRepositorio rutaColoniaRepositorio,
                          CiudadanoRepositorio ciudadanoRepositorio,
                          JwtUtil jwtUtil) {
        this.rutaServicio = rutaServicio;
        this.rutaColoniaRepositorio = rutaColoniaRepositorio;
        this.ciudadanoRepositorio = ciudadanoRepositorio;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ruta> crear(@RequestBody Ruta ruta) {
        return ResponseEntity.ok(rutaServicio.crearRuta(ruta));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<RutaDTO> obtenerPorId(@PathVariable Integer id) {
        Ruta ruta = rutaServicio.obtenerRutaPorId(id);
        return ResponseEntity.ok(toDTO(ruta));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<List<RutaDTO>> listarTodas() {
        List<RutaDTO> dtos = rutaServicio.obtenerTodosRutas().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Ruta> actualizar(@RequestBody Ruta ruta) {
        return ResponseEntity.ok(rutaServicio.actualizarRuta(ruta));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        rutaServicio.eliminarRuta(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<List<RutaDTO>> buscarPorNombre(@RequestParam String q) {
        List<RutaDTO> dtos = rutaServicio.buscarPorNombreContiene(q).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/activas")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<List<RutaDTO>> listarActivas() {
        List<RutaDTO> dtos = rutaServicio.bucarPorEstadoActivo().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/residuo-hoy")
    @PreAuthorize("hasRole('CIUDADANO')")
    public ResponseEntity<ResiduoDiaDTO> obtenerResiduoHoy(@AuthenticationPrincipal UserDetails userDetails,
                                                           @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Integer idUsuario = jwtUtil.extractClaim(token, claims -> claims.get("idUsuario", Integer.class));

        Ciudadano ciudadano = ciudadanoRepositorio.findByPersonaUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado"));

        LocalDate hoy = LocalDate.now();
        
        RutaColonia rutaColonia = rutaColoniaRepositorio
                .findByColoniaIdColoniaAndFechaRecoleccion(ciudadano.getColonia().getIdColonia(), hoy)
                .orElse(null);

        if (rutaColonia == null) {
            return ResponseEntity.ok(new ResiduoDiaDTO(
                    hoy,
                    null,
                    "No hay recolección programada para hoy",
                    ciudadano.getColonia().getNombre()
            ));
        }

        String nombreTipoResiduo = rutaColonia.getTipoResiduo() != null 
                ? rutaColonia.getTipoResiduo().getNombre() 
                : null;

        return ResponseEntity.ok(new ResiduoDiaDTO(
                rutaColonia.getFechaRecoleccion(),
                nombreTipoResiduo,
                rutaColonia.getRuta().getNombre(),
                rutaColonia.getColonia().getNombre()
        ));
    }

    private RutaDTO toDTO(Ruta ruta) {
        return new RutaDTO(
                ruta.getIdRuta(),
                ruta.getNombre(),
                ruta.getDescripcion(),
                ruta.getActiva()
        );
    }
}
