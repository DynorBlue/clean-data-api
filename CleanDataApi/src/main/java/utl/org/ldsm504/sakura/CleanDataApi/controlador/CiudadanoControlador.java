package utl.org.ldsm504.sakura.CleanDataApi.controlador;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utl.org.ldsm504.sakura.CleanDataApi.dto.RegistroCiudadanoRequest;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.*;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.ColoniaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.CiudadanoServicio;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/ciudadanos")
public class CiudadanoControlador {
    private final CiudadanoServicio ciudadanoServicio;
    private final ColoniaRepositorio coloniaRepositorio;

    public CiudadanoControlador(CiudadanoServicio ciudadanoServicio,
                               ColoniaRepositorio coloniaRepositorio) {
        this.ciudadanoServicio = ciudadanoServicio;
        this.coloniaRepositorio = coloniaRepositorio;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody RegistroCiudadanoRequest dto) {

        Colonia colonia = coloniaRepositorio.findById(dto.getIdColonia())//usar el getcolonia
                .orElseThrow(() -> new RuntimeException("Colonia no existe"));

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setContrasena(dto.getContrasena()); // luego va BCrypt
        usuario.setActivo(true);
        usuario.setTipoUsuario(TipoUsuario.CIUDADANO);
        usuario.setFechaRegistro(LocalDateTime.now());

        Persona persona = new Persona();
        persona.setNombre(dto.getNombre());
        persona.setTelefono(dto.getTelefono());

        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setDireccionCalle(dto.getDireccionCalle());
        ciudadano.setColonia(colonia);

        return ResponseEntity.ok(
                ciudadanoServicio.registrarCiudadano(usuario, persona, ciudadano)
        );
    }
}
