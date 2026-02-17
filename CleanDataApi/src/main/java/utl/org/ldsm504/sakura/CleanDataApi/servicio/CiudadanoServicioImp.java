package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Ciudadano;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Persona;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Usuario;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.CiudadanoRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.PersonaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.UsuarioRepositorio;

import java.util.List;

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
    @Override
    public List<Ciudadano> obtenerTodos() {
        return ciudadanoRepositorio.findAll();
    }

    @Override
    public Ciudadano obtenerPorId(Integer id) {
        return ciudadanoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado con id " + id));
    }

    @Override
    @Transactional
    public Ciudadano actualizar(Integer id, Ciudadano datos) {

        Ciudadano existente = obtenerPorId(id);

        if (datos.getDireccionCalle() != null)
            existente.setDireccionCalle(datos.getDireccionCalle());

        if (datos.getColonia() != null)
            existente.setColonia(datos.getColonia());

        return ciudadanoRepositorio.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        if (!ciudadanoRepositorio.existsById(id)) {
            throw new RuntimeException("No existe ciudadano con id " + id);
        }
        ciudadanoRepositorio.deleteById(id);
    }
}
