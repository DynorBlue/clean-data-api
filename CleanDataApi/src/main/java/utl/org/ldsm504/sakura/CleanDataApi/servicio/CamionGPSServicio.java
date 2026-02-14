package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.CamionGPS;

public interface CamionGPSServicio {
    CamionGPS crearOActualizarGPS(CamionGPS gps);
    CamionGPS obtenerPorId(Integer id);
    CamionGPS obtenerPorCamion(Integer idCamion);
    void eliminarGPS(Integer id);
}
