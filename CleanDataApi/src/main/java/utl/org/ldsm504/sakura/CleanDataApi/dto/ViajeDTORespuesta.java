package utl.org.ldsm504.sakura.CleanDataApi.dto;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoViaje;

import java.time.LocalDateTime;

public class ViajeDTORespuesta {
    private Integer idViaje;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private EstadoViaje estado;
    private CamionDTO camion;
    private ConductorDTO conductor;
    private RutaDTO ruta;
    private TipoResiduoDTO tipoResiduo;

    public ViajeDTORespuesta() {
    }

    public Integer getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(Integer idViaje) {
        this.idViaje = idViaje;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public EstadoViaje getEstado() {
        return estado;
    }

    public void setEstado(EstadoViaje estado) {
        this.estado = estado;
    }

    public CamionDTO getCamion() {
        return camion;
    }

    public void setCamion(CamionDTO camion) {
        this.camion = camion;
    }

    public ConductorDTO getConductor() {
        return conductor;
    }

    public void setConductor(ConductorDTO conductor) {
        this.conductor = conductor;
    }

    public RutaDTO getRuta() {
        return ruta;
    }

    public void setRuta(RutaDTO ruta) {
        this.ruta = ruta;
    }

    public TipoResiduoDTO getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(TipoResiduoDTO tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }
}
