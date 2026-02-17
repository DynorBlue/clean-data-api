package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.Camion;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoCamion;

import java.util.List;

public interface CamionServicio {

    Camion crearCamion(Camion camion);
    Camion obtenerCamionPorId(Integer id);
    List<Camion> obtenerTodosCamiones();
    Camion actualizarCamion(Camion camion);
    void eliminarCamion(Integer id);

    // Personalizado:
    List<Camion> obtenerCamionPorEstado(EstadoCamion estadoCamion);
    Camion actualizarCamionPorId(Integer id, Camion datos);
}
