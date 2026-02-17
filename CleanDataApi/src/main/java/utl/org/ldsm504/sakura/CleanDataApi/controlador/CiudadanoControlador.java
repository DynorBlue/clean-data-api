package utl.org.ldsm504.sakura.CleanDataApi.controlador;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.AuthResponse;
import utl.org.ldsm504.sakura.CleanDataApi.dto.CiudadanoDTO;
import utl.org.ldsm504.sakura.CleanDataApi.dto.ColoniaDTO;
import utl.org.ldsm504.sakura.CleanDataApi.dto.PersonaDTO;
import utl.org.ldsm504.sakura.CleanDataApi.dto.RegistroCiudadanoRequest;
import utl.org.ldsm504.sakura.CleanDataApi.config.JwtUtil;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.*;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.ColoniaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.UsuarioRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.CiudadanoServicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ciudadanos")
public class CiudadanoControlador {
    private final CiudadanoServicio ciudadanoServicio;
    private final ColoniaRepositorio coloniaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final JwtUtil jwtUtil;

    public CiudadanoControlador(CiudadanoServicio ciudadanoServicio,
                               ColoniaRepositorio coloniaRepositorio,
                               UsuarioRepositorio usuarioRepositorio,
                               JwtUtil jwtUtil) {
        this.ciudadanoServicio = ciudadanoServicio;
        this.coloniaRepositorio = coloniaRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody RegistroCiudadanoRequest dto) {

        Colonia colonia = coloniaRepositorio.findById(dto.getIdColonia())
                .orElseThrow(() -> new RuntimeException("Colonia no existe"));

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setContrasena(dto.getPassword());
        usuario.setActivo(true);
        usuario.setTipoUsuario(TipoUsuario.CIUDADANO);
        usuario.setFechaRegistro(LocalDateTime.now());

        Persona persona = new Persona();
        persona.setNombre(dto.getNombre());
        persona.setTelefono(dto.getTelefono());

        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setDireccionCalle(dto.getDireccionCalle());
        ciudadano.setColonia(colonia);

        Ciudadano registrado = ciudadanoServicio.registrarCiudadano(usuario, persona, ciudadano);

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
    public List<CiudadanoDTO> obtenerTodos() {
        return ciudadanoServicio.obtenerTodos().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    public ResponseEntity<CiudadanoDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(toDTO(ciudadanoServicio.obtenerPorId(id)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Ciudadano actualizar(@PathVariable Integer id,
                                @RequestBody Ciudadano datos) {
        return ciudadanoServicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void eliminar(@PathVariable Integer id) {
        ciudadanoServicio.eliminar(id);
    }

    private CiudadanoDTO toDTO(Ciudadano ciudadano) {
        PersonaDTO personaDTO = null;
        if (ciudadano.getPersona() != null) {
            personaDTO = new PersonaDTO(
                    ciudadano.getPersona().getIdPersona(),
                    ciudadano.getPersona().getNombre(),
                    ciudadano.getPersona().getTelefono()
            );
        }

        ColoniaDTO coloniaDTO = null;
        if (ciudadano.getColonia() != null) {
            coloniaDTO = new ColoniaDTO(
                    ciudadano.getColonia().getIdColonia(),
                    ciudadano.getColonia().getNombre(),
                    ciudadano.getColonia().getCodigoPostal(),
                    ciudadano.getColonia().getLatitud() != null ? ciudadano.getColonia().getLatitud().doubleValue() : null,
                    ciudadano.getColonia().getLongitud() != null ? ciudadano.getColonia().getLongitud().doubleValue() : null
            );
        }

        return new CiudadanoDTO(
                ciudadano.getIdPersona(),
                ciudadano.getDireccionCalle(),
                coloniaDTO,
                personaDTO
        );
    }

}
