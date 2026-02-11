package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import jakarta.persistence.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "camion_gps")
public class CamionGPS {

    @Id
    @Column(name = "id_camion")
    private Integer idCamion;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_camion")
    private Camion camion;

    @Column(name = "latitud", precision = 10, scale = 7)
    private BigDecimal latitud;

    @Column(name = "longitud", precision = 10, scale = 7)
    private BigDecimal longitud;

    @Column(name = "velocidad", precision = 6, scale = 2)
    private BigDecimal velocidad;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public CamionGPS() {
    }

    public CamionGPS(Camion camion, LocalDateTime fechaActualizacion, Integer idCamion, BigDecimal latitud, BigDecimal longitud, BigDecimal velocidad) {
        this.camion = camion;
        this.fechaActualizacion = fechaActualizacion;
        this.idCamion = idCamion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.velocidad = velocidad;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Integer idCamion) {
        this.idCamion = idCamion;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public BigDecimal getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(BigDecimal velocidad) {
        this.velocidad = velocidad;
    }
}
