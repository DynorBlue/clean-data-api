package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoViaje;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Viaje;

import java.util.List;

public interface ViajeServicio {
    Viaje crearViaje(Viaje viaje);
    Viaje obtenerViajePorId(Integer id);
    List<Viaje> obtenerTodosViajes();
    Viaje actualizarViaje(Viaje viaje);
    void eliminarViaje(Integer id);
    List<Viaje> obtenerPorEstado(EstadoViaje estado);
    List<Viaje> obtenerPorConductor(Integer idConductor);
    Viaje iniciarViaje(Integer id);
    Viaje finalizarViaje(Integer id);
}
