package utl.org.ldsm504.sakura.CleanDataApi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Camion;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoCamion;

import java.util.List;

public interface CamionRepositorio extends JpaRepository<Camion, Integer> {
    List<Camion> findByEstado(EstadoCamion estado);
}
