package utl.org.ldsm504.sakura.CleanDataApi.repositorio;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Ruta;

import java.util.List;

public interface RutaRepositorio extends JpaRepository<Ruta, Integer> {
    List<Ruta> findByNombreContainingIgnoreCase(String nombre);
    List<Ruta> findByActivaTrue();
}
