package utl.org.ldsm504.sakura.CleanDataApi.servicio;


import utl.org.ldsm504.sakura.CleanDataApi.modelo.Ruta;

import java.util.List;

public interface RutaServicio {
    Ruta crearRuta(Ruta ruta);
    Ruta obtenerRutaPorId(Integer id);
    List<Ruta> obtenerTodosRutas();
    Ruta actualizarRuta(Ruta ruta);
    void eliminarRuta(Integer id);

    List<Ruta> buscarPorNombreContiene(String parteNombre);
    List<Ruta> bucarPorEstadoActivo();
}
