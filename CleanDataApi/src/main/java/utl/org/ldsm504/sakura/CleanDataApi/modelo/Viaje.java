package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "viaje")
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viaje")
    private Integer idViaje;

    @ManyToOne
    @JoinColumn(name = "id_camion", nullable = false)
    private Camion camion;

    @ManyToOne
    @JoinColumn(name = "id_conductor", nullable = false)
    private Conductor conductor;

    @ManyToOne
    @JoinColumn(name = "id_ruta", nullable = false)
    private Ruta ruta;

    @ManyToOne
    @JoinColumn(name = "id_tipo_residuo", nullable = false)
    private TipoResiduo tipoResiduo;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoViaje estado;

    public Viaje() {
    }

    public Viaje(Camion camion, Conductor conductor, EstadoViaje estado, LocalDateTime fechaFin, LocalDateTime fechaInicio, Integer idViaje, Ruta ruta, TipoResiduo tipoResiduo) {
        this.camion = camion;
        this.conductor = conductor;
        this.estado = estado;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.idViaje = idViaje;
        this.ruta = ruta;
        this.tipoResiduo = tipoResiduo;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public EstadoViaje getEstado() {
        return estado;
    }

    public void setEstado(EstadoViaje estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Integer getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(Integer idViaje) {
        this.idViaje = idViaje;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public TipoResiduo getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(TipoResiduo tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }
}
