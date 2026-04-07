package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.dto.ConductorUpdateRequest;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Conductor;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoOperativo;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Persona;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Usuario;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.ConductorRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.PersonaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.UsuarioRepositorio;

import java.util.List;

@Service
public class ConductorServicioImp implements ConductorServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PersonaRepositorio personaRepositorio;
    private final ConductorRepositorio conductorRepositorio;

    public ConductorServicioImp(
            UsuarioRepositorio usuarioRepositorio,
            PersonaRepositorio personaRepositorio,
            ConductorRepositorio conductorRepositorio
    ) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.personaRepositorio = personaRepositorio;
        this.conductorRepositorio = conductorRepositorio;
    }

    @Override
    @Transactional
    public Conductor registrarConductor(Usuario usuario, Persona persona, Conductor conductor) {
        Usuario u = usuarioRepositorio.save(usuario);

        persona.setUsuario(u);
        Persona p = personaRepositorio.save(persona);

        conductor.setPersona(p);

        return conductorRepositorio.save(conductor);
    }
    @Override
    public List<Conductor> obtenerTodos() {
        return conductorRepositorio.findAll();
    }

    @Override
    public Conductor obtenerPorId(Integer id) {
        return conductorRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado con id " + id));
    }

    @Override
    public List<Conductor> obtenerPorEstado(EstadoOperativo estado) {
        return conductorRepositorio.findByEstadoOperativo(estado);
    }

    @Override
    @Transactional
    public Conductor actualizar(Integer id, ConductorUpdateRequest dto) {

        Conductor existente = obtenerPorId(id);

        if (dto.getLicencia() != null)
            existente.setLicencia(dto.getLicencia());

        if (dto.getFechaAlta() != null)
            existente.setFechaAlta(dto.getFechaAlta());

        if (dto.getFechaBaja() != null)
            existente.setFechaBaja(dto.getFechaBaja());

        if (dto.getEstadoOperativo() != null)
            existente.setEstadoOperativo(dto.getEstadoOperativo());

        Persona personaExistente = existente.getPersona();
        if (dto.getNombre() != null) {
            personaExistente.setNombre(dto.getNombre());
        }
        if (dto.getTelefono() != null) {
            personaExistente.setTelefono(dto.getTelefono());
        }
        personaRepositorio.save(personaExistente);

        return conductorRepositorio.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if (!conductorRepositorio.existsById(id)) {
            throw new RuntimeException("No existe conductor con id " + id);
        }
        conductorRepositorio.deleteById(id);
    }
}
