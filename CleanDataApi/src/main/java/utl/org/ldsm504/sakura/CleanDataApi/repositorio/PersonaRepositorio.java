package utl.org.ldsm504.sakura.CleanDataApi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Persona;

import java.util.Optional;

public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {
    Optional<Persona> findByUsuarioIdUsuario(Integer idUsuario);
}
