package utl.org.ldsm504.sakura.CleanDataApi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Conductor;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoOperativo;

import java.util.List;

public interface ConductorRepositorio extends JpaRepository<Conductor, Integer> {
    List<Conductor> findByEstadoOperativo(EstadoOperativo estadoOperativo);

}
