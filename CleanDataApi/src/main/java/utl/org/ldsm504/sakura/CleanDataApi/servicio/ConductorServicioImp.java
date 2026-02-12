package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Conductor;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Persona;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Usuario;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.ConductorRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.PersonaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.UsuarioRepositorio;

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
        // Guardar Usuario
        Usuario u = usuarioRepositorio.save(usuario);

        // Asociar Persona con Usuario y guardar
        persona.setUsuario(u);
        Persona p = personaRepositorio.save(persona);

        // Asociar Conductor con Persona y guardar
        conductor.setPersona(p);

        return conductorRepositorio.save(conductor);
    }
}
