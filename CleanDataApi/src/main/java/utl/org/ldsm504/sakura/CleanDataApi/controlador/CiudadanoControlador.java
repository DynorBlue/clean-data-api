package utl.org.ldsm504.sakura.CleanDataApi.controlador;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.AuthResponse;
import utl.org.ldsm504.sakura.CleanDataApi.dto.RegistroCiudadanoRequest;
import utl.org.ldsm504.sakura.CleanDataApi.config.JwtUtil;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.*;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.ColoniaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.UsuarioRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.CiudadanoServicio;

import java.time.LocalDateTime;
import java.util.List;

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
        usuario.setContrasena(dto.getContrasena());
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
    public List<Ciudadano> obtenerTodos() {
        return ciudadanoServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    public Ciudadano obtenerPorId(@PathVariable Integer id) {
        return ciudadanoServicio.obtenerPorId(id);
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

}
