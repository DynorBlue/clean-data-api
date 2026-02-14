package utl.org.ldsm504.sakura.CleanDataApi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RecoleccionDTO {
    private Integer idRecoleccion;
    private Integer idViaje;
    private Integer idTipoResiduo;
    private BigDecimal volumenM3;
    private BigDecimal pesoKg;
    private LocalDateTime fechaRegistro;

    public RecoleccionDTO() {
    }

    public Integer getIdRecoleccion() {
        return idRecoleccion;
    }

    public void setIdRecoleccion(Integer idRecoleccion) {
        this.idRecoleccion = idRecoleccion;
    }

    public Integer getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(Integer idViaje) {
        this.idViaje = idViaje;
    }

    public Integer getIdTipoResiduo() {
        return idTipoResiduo;
    }

    public void setIdTipoResiduo(Integer idTipoResiduo) {
        this.idTipoResiduo = idTipoResiduo;
    }

    public BigDecimal getVolumenM3() {
        return volumenM3;
    }

    public void setVolumenM3(BigDecimal volumenM3) {
        this.volumenM3 = volumenM3;
    }

    public BigDecimal getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(BigDecimal pesoKg) {
        this.pesoKg = pesoKg;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
