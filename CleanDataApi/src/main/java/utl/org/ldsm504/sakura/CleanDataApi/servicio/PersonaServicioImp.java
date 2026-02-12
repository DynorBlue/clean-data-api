package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Persona;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.PersonaRepositorio;

@Service
public class PersonaServicioImp implements PersonaServicio {
    private final PersonaRepositorio personaRepositorio;

    public PersonaServicioImp(PersonaRepositorio personaRepository) {
        this.personaRepositorio = personaRepository;
    }

    @Override
    public Persona crearPersona(Persona persona) {
        return personaRepositorio.save(persona);
    }

    @Override
    public Persona obtenerPorId(Integer id) {
        return personaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
    }
}
