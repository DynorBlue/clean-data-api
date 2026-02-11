package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Ciudadano;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Persona;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Usuario;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.CiudadanoRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.PersonaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.UsuarioRepositorio;

@Service
public class CiudadanoServicioImp implements CiudadanoServicio{
    private final UsuarioRepositorio usuarioRepositorio;
    private final PersonaRepositorio personaRepositorio;
    private final CiudadanoRepositorio ciudadanoRepositorio;

    public CiudadanoServicioImp(
            UsuarioRepositorio usuarioRepository,
            PersonaRepositorio personaRepository,
            CiudadanoRepositorio ciudadanoRepository
    ) {
        this.usuarioRepositorio = usuarioRepository;
        this.personaRepositorio = personaRepository;
        this.ciudadanoRepositorio = ciudadanoRepository;
    }

    @Override
    @Transactional
    public Ciudadano registrarCiudadano(Usuario usuario, Persona persona, Ciudadano ciudadano) {

        Usuario u = usuarioRepositorio.save(usuario);
        persona.setUsuario(u);
        Persona p = personaRepositorio.save(persona);
        ciudadano.setPersona(p);

        return ciudadanoRepositorio.save(ciudadano);
    }
}
