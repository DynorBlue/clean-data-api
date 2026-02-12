package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.Persona;

public interface PersonaServicio {
    Persona crearPersona(Persona persona);
    Persona obtenerPorId(Integer id);
}
