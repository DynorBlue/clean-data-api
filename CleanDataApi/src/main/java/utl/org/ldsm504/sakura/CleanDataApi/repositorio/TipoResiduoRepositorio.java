package utl.org.ldsm504.sakura.CleanDataApi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.TipoResiduo;

import java.util.List;

public interface TipoResiduoRepositorio extends JpaRepository<TipoResiduo, Integer> {
    List<TipoResiduo>findByNombreContainingIgnoreCase(String nombre);

}
