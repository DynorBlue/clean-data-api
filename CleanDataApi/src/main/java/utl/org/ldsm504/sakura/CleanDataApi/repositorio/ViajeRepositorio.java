package utl.org.ldsm504.sakura.CleanDataApi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoViaje;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Viaje;

import java.util.List;

public interface ViajeRepositorio extends JpaRepository<Viaje, Integer> {
    List<Viaje> findByEstado(EstadoViaje estado);
    List<Viaje> findByConductorIdPersona(Integer idConductor);
    List<Viaje> findByCamionIdCamion(Integer idCamion);
}
