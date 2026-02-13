package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.Ciudadano;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Persona;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Usuario;

import java.util.List;

public interface CiudadanoServicio {
    Ciudadano registrarCiudadano(Usuario usuario, Persona persona, Ciudadano ciudadano);
    List<Ciudadano> obtenerTodos();

    Ciudadano obtenerPorId(Integer id);

    Ciudadano actualizar(Integer id, Ciudadano datos);

    void eliminar(Integer id);
}
