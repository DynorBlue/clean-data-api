package utl.org.ldsm504.sakura.CleanDataApi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Colonia;

import java.util.List;
import java.util.Optional;

public interface ColoniaRepositorio extends JpaRepository<Colonia, Integer> {
    List<Colonia> findByCodigoPostal(String cp);
    Optional<Colonia> findByNombreIgnoreCase(String nombre);
    List<Colonia> findByNombreContainingIgnoreCase(String nombre);
}
