package utl.org.ldsm504.sakura.CleanDataApi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Usuario;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
