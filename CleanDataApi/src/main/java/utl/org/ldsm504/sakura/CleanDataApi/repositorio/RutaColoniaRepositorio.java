package utl.org.ldsm504.sakura.CleanDataApi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.RutaColonia;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RutaColoniaRepositorio extends JpaRepository<RutaColonia, Integer> {
    List<RutaColonia> findByColoniaIdColonia(Integer idColonia);
    List<RutaColonia> findByRutaIdRuta(Integer idRuta);
    Optional<RutaColonia> findByColoniaIdColoniaAndFechaRecoleccion(Integer idColonia, LocalDate fechaRecoleccion);
}
