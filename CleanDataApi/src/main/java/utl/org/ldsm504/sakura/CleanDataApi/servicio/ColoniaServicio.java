package utl.org.ldsm504.sakura.CleanDataApi.servicio;


import utl.org.ldsm504.sakura.CleanDataApi.modelo.Colonia;

import java.util.List;

public interface ColoniaServicio {
    Colonia crearColonia(Colonia colonia);
    Colonia obtenerColoniaPorId(Integer id);
    List<Colonia> obtenerTodasColonia();
    Colonia actualizarColonia(Colonia colonia);
    void eliminarColonia(Integer id);


    // metodos personalizados
    Colonia actualizarColoniaPorId(Integer id, Colonia datos);
    Colonia buscarPorNombreExacto(String nombre);
    List<Colonia> buscarPorNombreContiene(String parteNombre);


}
