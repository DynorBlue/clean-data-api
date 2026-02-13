package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.RegistroConductorRequest;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.*;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.ConductorServicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/conductores")
public class ConductorControlador {

    private final ConductorServicio conductorServicio;

    public ConductorControlador(ConductorServicio conductorServicio) {
        this.conductorServicio = conductorServicio;
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

        return ResponseEntity.ok(
                conductorServicio.registrarConductor(usuario, persona, conductor)
        );
    }
    @GetMapping
    public List<Conductor> obtenerTodos() {
        return conductorServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Conductor obtenerPorId(@PathVariable Integer id) {
        return conductorServicio.obtenerPorId(id);
    }

    @GetMapping("/estado/{estado}")
    public List<Conductor> obtenerPorEstado(@PathVariable EstadoOperativo estado) {
        return conductorServicio.obtenerPorEstado(estado);
    }

    @PutMapping("/{id}")
    public Conductor actualizar(@PathVariable Integer id,
                                @RequestBody Conductor datos) {
        return conductorServicio.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        conductorServicio.eliminar(id);
    }
}