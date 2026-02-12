package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Usuario;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicioImp implements UsuarioServicio {
    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioServicioImp(UsuarioRepositorio usuarioRepository) {
        this.usuarioRepositorio = usuarioRepository;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public boolean existeEmail(String email) {
        return usuarioRepositorio.existsByEmail(email);
    }

    @Override
    public Usuario obtenerPorId(Integer id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));
    }
}
