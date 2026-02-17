package utl.org.ldsm504.sakura.CleanDataApi.dto;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.TipoUsuario;

public class UsuarioDTORespuesta {
    private Integer idUsuario;
    private String email;
    private TipoUsuario tipoUsuario;

    public UsuarioDTORespuesta() {
    }

    public UsuarioDTORespuesta(Integer idUsuario, String email, TipoUsuario tipoUsuario) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
