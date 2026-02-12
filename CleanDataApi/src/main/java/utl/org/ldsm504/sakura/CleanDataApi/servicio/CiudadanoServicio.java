package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.Ciudadano;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Persona;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Usuario;

public interface CiudadanoServicio {
    Ciudadano registrarCiudadano(Usuario usuario, Persona persona, Ciudadano ciudadano);

}
