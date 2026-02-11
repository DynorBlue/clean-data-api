package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Conductor;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Persona;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.ConductorRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.PersonaRepositorio;

@Service
public class ConductorServicioImp implements ConductorServicio{
    private final ConductorRepositorio conductorRepositorio;
    private final PersonaRepositorio personaRepositorio;

    public ConductorServicioImp(ConductorRepositorio conductorRepositorio,
                                PersonaRepositorio personaRepositorio) {
        this.conductorRepositorio = conductorRepositorio;
        this.personaRepositorio = personaRepositorio;
    }

    @Override
    @Transactional
    public Conductor registrarConductor(Persona persona, Conductor conductor) {

        Persona p = personaRepositorio.save(persona);
        conductor.setPersona(p);

        return conductorRepositorio.save(conductor);
    }
}
