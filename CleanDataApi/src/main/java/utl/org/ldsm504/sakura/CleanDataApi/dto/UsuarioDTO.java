package utl.org.ldsm504.sakura.CleanDataApi.dto;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.TipoUsuario;

public class UsuarioDTO {
    private Integer idUsuario;
    private String email;
    private TipoUsuario tipoUsuario;
    private Boolean activo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Integer idUsuario, String email, TipoUsuario tipoUsuario, Boolean activo) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        this.activo = activo;
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

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
