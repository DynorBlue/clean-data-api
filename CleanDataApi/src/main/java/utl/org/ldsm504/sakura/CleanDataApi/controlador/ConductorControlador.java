package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.AuthResponse;
import utl.org.ldsm504.sakura.CleanDataApi.dto.ConductorDTO;
import utl.org.ldsm504.sakura.CleanDataApi.dto.PersonaDTO;
import utl.org.ldsm504.sakura.CleanDataApi.dto.RegistroConductorRequest;
import utl.org.ldsm504.sakura.CleanDataApi.config.JwtUtil;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.*;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.UsuarioRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.ConductorServicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/conductores")
public class ConductorControlador {

    private final ConductorServicio conductorServicio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final JwtUtil jwtUtil;

    public ConductorControlador(ConductorServicio conductorServicio,
                                UsuarioRepositorio usuarioRepositorio,
                                JwtUtil jwtUtil) {
        this.conductorServicio = conductorServicio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@Valid @RequestBody RegistroConductorRequest dto) {

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setContrasena(dto.getPassword());
        usuario.setActivo(true);
        usuario.setTipoUsuario(TipoUsuario.CONDUCTOR);
        usuario.setFechaRegistro(LocalDateTime.now());

        Persona persona = new Persona();
        persona.setNombre(dto.getNombre());
        persona.setTelefono(dto.getTelefono());

        Conductor conductor = new Conductor();
        conductor.setLicencia(dto.getLicencia());
        conductor.setFechaAlta(dto.getFechaAlta() != null ? dto.getFechaAlta() : LocalDate.now());
        conductor.setEstadoOperativo(EstadoOperativo.ACTIVO);

        Conductor registrado = conductorServicio.registrarConductor(usuario, persona, conductor);

        Usuario usuarioGuardado = usuarioRepositorio.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Error al obtener usuario registrado"));

        String token = jwtUtil.generateToken(
                usuarioGuardado.getEmail(),
                usuarioGuardado.getTipoUsuario().name(),
                usuarioGuardado.getIdUsuario()
        );

        AuthResponse response = new AuthResponse(
                token,
                usuarioGuardado.getEmail(),
                usuarioGuardado.getTipoUsuario(),
                usuarioGuardado.getIdUsuario(),
                registrado.getPersona().getNombre(),
                registrado.getPersona().getIdPersona()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<ConductorDTO> obtenerTodos() {
        return conductorServicio.obtenerTodos().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public ResponseEntity<ConductorDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(toDTO(conductorServicio.obtenerPorId(id)));
    }

    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ConductorDTO> obtenerPorEstado(@PathVariable EstadoOperativo estado) {
        return conductorServicio.obtenerPorEstado(estado).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ConductorDTO> actualizar(@PathVariable Integer id,
                                @RequestBody Conductor datos) {
        return ResponseEntity.ok(toDTO(conductorServicio.actualizar(id, datos)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminar(@PathVariable Integer id) {
        conductorServicio.eliminar(id);
    }

    private ConductorDTO toDTO(Conductor conductor) {
        PersonaDTO personaDTO = null;
        if (conductor.getPersona() != null) {
            personaDTO = new PersonaDTO(
                    conductor.getPersona().getIdPersona(),
                    conductor.getPersona().getNombre(),
                    conductor.getPersona().getTelefono()
            );
        }

        return new ConductorDTO(
                conductor.getIdPersona(),
                conductor.getLicencia(),
                conductor.getFechaAlta(),
                conductor.getFechaBaja(),
                conductor.getEstadoOperativo(),
                personaDTO
        );
    }
}
