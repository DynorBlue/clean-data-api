package utl.org.ldsm504.sakura.CleanDataApi.dto;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoReporte;

import java.time.LocalDateTime;

public class ReporteDTORespuesta {
    private Integer idReporte;
    private String descripcion;
    private LocalDateTime fecha;
    private EstadoReporte estado;
    private UsuarioDTORespuesta usuario;
    private ColoniaDTO colonia;
    private TipoResiduoDTO tipoResiduo;

    public ReporteDTORespuesta() {
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
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

    public UsuarioDTORespuesta getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTORespuesta usuario) {
        this.usuario = usuario;
    }

    public ColoniaDTO getColonia() {
        return colonia;
    }

    public void setColonia(ColoniaDTO colonia) {
        this.colonia = colonia;
    }

    public TipoResiduoDTO getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(TipoResiduoDTO tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }
}
