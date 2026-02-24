package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.config.CustomUserDetailsService;
import utl.org.ldsm504.sakura.CleanDataApi.config.JwtUtil;
import utl.org.ldsm504.sakura.CleanDataApi.dto.AuthResponse;
import utl.org.ldsm504.sakura.CleanDataApi.dto.LoginRequest;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Persona;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Usuario;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.PersonaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.UsuarioRepositorio;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final UsuarioRepositorio usuarioRepositorio;
    private final PersonaRepositorio personaRepositorio;

    public AuthController(AuthenticationManager authenticationManager,
                         CustomUserDetailsService userDetailsService,
                         JwtUtil jwtUtil,
                         UsuarioRepositorio usuarioRepositorio,
                         PersonaRepositorio personaRepositorio) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.usuarioRepositorio = usuarioRepositorio;
        this.personaRepositorio = personaRepositorio;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales inválidas");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        Usuario usuario = usuarioRepositorio.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtUtil.generateToken(
                userDetails.getUsername(),
                usuario.getTipoUsuario().name(),
                usuario.getIdUsuario()
        );

        String nombre = null;
        Integer idPersona = null;
        try {
            Persona persona = personaRepositorio.findByUsuarioIdUsuario(usuario.getIdUsuario())
                    .orElse(null);
            if (persona != null) {
                nombre = persona.getNombre();
                idPersona = persona.getIdPersona();
            }
        } catch (Exception e) {
        }

        AuthResponse response = new AuthResponse(
                token,
                usuario.getEmail(),
                usuario.getTipoUsuario(),
                usuario.getIdUsuario(),
                nombre,
                idPersona
        );

        return ResponseEntity.ok(response);
    }
}
