package utl.org.ldsm504.sakura.CleanDataApi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Recoleccion;

import java.util.List;

public interface RecoleccionRepositorio extends JpaRepository<Recoleccion, Integer> {
    List<Recoleccion> findByViajeIdViaje(Integer idViaje);
}
