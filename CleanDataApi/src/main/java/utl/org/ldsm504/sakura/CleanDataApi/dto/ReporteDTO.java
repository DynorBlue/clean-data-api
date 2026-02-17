package utl.org.ldsm504.sakura.CleanDataApi.dto;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoReporte;

import java.time.LocalDateTime;

public class ReporteDTO {
    private Integer idReporte;
    private Integer idUsuario;
    private Integer idColonia;
    private Integer idTipoResiduo;
    private String descripcion;
    private LocalDateTime fecha;
    private EstadoReporte estado;

    public ReporteDTO() {
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(Integer idColonia) {
        this.idColonia = idColonia;
    }

    public Integer getIdTipoResiduo() {
        return idTipoResiduo;
    }

    public void setIdTipoResiduo(Integer idTipoResiduo) {
        this.idTipoResiduo = idTipoResiduo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public EstadoReporte getEstado() {
        return estado;
    }

    public void setEstado(EstadoReporte estado) {
        this.estado = estado;
    }
}
