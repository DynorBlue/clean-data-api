package utl.org.ldsm504.sakura.CleanDataApi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.CamionGPS;

import java.util.Optional;

public interface CamionGPSRepositorio extends JpaRepository<CamionGPS, Integer> {
    Optional<CamionGPS> findByCamionIdCamion(Integer idCamion);
}
