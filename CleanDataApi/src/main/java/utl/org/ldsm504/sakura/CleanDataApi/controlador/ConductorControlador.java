package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.AuthResponse;
import utl.org.ldsm504.sakura.CleanDataApi.dto.RegistroConductorRequest;
import utl.org.ldsm504.sakura.CleanDataApi.config.JwtUtil;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.*;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.UsuarioRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.ConductorServicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    public ResponseEntity<?> registrar(@RequestBody RegistroConductorRequest dto) {

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setContrasena(dto.getContrasena());
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
                usuarioGuardado.getIdUsuario()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Conductor> obtenerTodos() {
        return conductorServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CONDUCTOR')")
    public Conductor obtenerPorId(@PathVariable Integer id) {
        return conductorServicio.obtenerPorId(id);
    }

    @GetMapping("/estado/{estado}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Conductor> obtenerPorEstado(@PathVariable EstadoOperativo estado) {
        return conductorServicio.obtenerPorEstado(estado);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Conductor actualizar(@PathVariable Integer id,
                                @RequestBody Conductor datos) {
        return conductorServicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminar(@PathVariable Integer id) {
        conductorServicio.eliminar(id);
    }
}