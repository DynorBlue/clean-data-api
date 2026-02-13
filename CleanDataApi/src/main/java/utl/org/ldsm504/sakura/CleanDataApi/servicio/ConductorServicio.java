package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.Conductor;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoOperativo;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Persona;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Usuario;

import java.util.List;

public interface ConductorServicio {

        Conductor registrarConductor(Usuario usuario, Persona persona, Conductor conductor);

        List<Conductor> obtenerTodos();

        Conductor obtenerPorId(Integer id);

        List<Conductor> obtenerPorEstado(EstadoOperativo estado);

        Conductor actualizar(Integer id, Conductor datos);

        void eliminar(Integer id);
}
