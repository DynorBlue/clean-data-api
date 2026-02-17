package utl.org.ldsm504.sakura.CleanDataApi.dto;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.TipoUsuario;

public class AuthResponse {

    private String token;
    private String email;
    private TipoUsuario tipoUsuario;
    private Integer idUsuario;

    public AuthResponse() {
    }

    public AuthResponse(String token, String email, TipoUsuario tipoUsuario, Integer idUsuario) {
        this.token = token;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
