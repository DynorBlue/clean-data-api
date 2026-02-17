package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.Recoleccion;

import java.util.List;

public interface RecoleccionServicio {
    Recoleccion crearRecoleccion(Recoleccion recoleccion);
    Recoleccion obtenerRecoleccionPorId(Integer id);
    List<Recoleccion> obtenerTodasRecolecciones();
    Recoleccion actualizarRecoleccion(Recoleccion recoleccion);
    void eliminarRecoleccion(Integer id);
    List<Recoleccion> obtenerPorViaje(Integer idViaje);
}
