package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.CamionGPS;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.CamionGPSRepositorio;

import java.time.LocalDateTime;

@Service
public class CamionGPSServicioImp implements CamionGPSServicio {

    private final CamionGPSRepositorio camionGPSRepositorio;

    public CamionGPSServicioImp(CamionGPSRepositorio camionGPSRepositorio) {
        this.camionGPSRepositorio = camionGPSRepositorio;
    }

    @Override
    @Transactional
    public CamionGPS crearOActualizarGPS(CamionGPS gps) {
        gps.setFechaActualizacion(LocalDateTime.now());
        return camionGPSRepositorio.save(gps);
    }

    @Override
    public CamionGPS obtenerPorId(Integer id) {
        return camionGPSRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("GPS no encontrado con id " + id));
    }

    @Override
    public CamionGPS obtenerPorCamion(Integer idCamion) {
        return camionGPSRepositorio.findByCamionIdCamion(idCamion)
                .orElseThrow(() -> new RuntimeException("GPS no encontrado para el camión con id " + idCamion));
    }

    @Override
    public void eliminarGPS(Integer id) {
        if (!camionGPSRepositorio.existsById(id)) {
            throw new RuntimeException("No existe GPS con id " + id);
        }
        camionGPSRepositorio.deleteById(id);
    }
}
