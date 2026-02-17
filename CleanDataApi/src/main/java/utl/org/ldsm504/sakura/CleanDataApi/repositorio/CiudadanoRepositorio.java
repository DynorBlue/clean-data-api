package utl.org.ldsm504.sakura.CleanDataApi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Ciudadano;

import java.util.Optional;

public interface CiudadanoRepositorio extends JpaRepository<Ciudadano, Integer> {
    @Query("SELECT c FROM Ciudadano c WHERE c.persona.usuario.idUsuario = :idUsuario")
    Optional<Ciudadano> findByPersonaUsuarioIdUsuario(@Param("idUsuario") Integer idUsuario);
}
