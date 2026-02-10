package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "recoleccion")
public class Recoleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recoleccion")
    private Integer idRecoleccion;

    @ManyToOne
    @JoinColumn(name = "id_viaje", nullable = false)
    private Viaje viaje;

    @ManyToOne
    @JoinColumn(name = "id_tipo_residuo", nullable = false)
    private TipoResiduo tipoResiduo;

    @Column(name = "volumen_m3", precision = 10, scale = 2)
    private BigDecimal volumenM3;

    @Column(name = "peso_kg", precision = 10, scale = 2)
    private BigDecimal pesoKg;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    public Recoleccion() {
    }

    public Recoleccion(LocalDateTime fechaRegistro, Integer idRecoleccion, BigDecimal pesoKg, TipoResiduo tipoResiduo, Viaje viaje, BigDecimal volumenM3) {
        this.fechaRegistro = fechaRegistro;
        this.idRecoleccion = idRecoleccion;
        this.pesoKg = pesoKg;
        this.tipoResiduo = tipoResiduo;
        this.viaje = viaje;
        this.volumenM3 = volumenM3;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdRecoleccion() {
        return idRecoleccion;
    }

    public void setIdRecoleccion(Integer idRecoleccion) {
        this.idRecoleccion = idRecoleccion;
    }

    public BigDecimal getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(BigDecimal pesoKg) {
        this.pesoKg = pesoKg;
    }

    public TipoResiduo getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(TipoResiduo tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public BigDecimal getVolumenM3() {
        return volumenM3;
    }

    public void setVolumenM3(BigDecimal volumenM3) {
        this.volumenM3 = volumenM3;
    }
}
