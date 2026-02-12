package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.Usuario;

public interface UsuarioServicio {
    Usuario crearUsuario(Usuario usuario);

    Usuario buscarPorEmail(String email);

    boolean existeEmail(String email);

    Usuario obtenerPorId(Integer id);
}
