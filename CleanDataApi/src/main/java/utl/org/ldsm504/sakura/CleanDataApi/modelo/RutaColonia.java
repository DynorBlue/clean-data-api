package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ruta_colonia")
public class RutaColonia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_ruta", nullable = false)
    private Ruta ruta;

    @ManyToOne
    @JoinColumn(name = "id_colonia", nullable = false)
    private Colonia colonia;

    @ManyToOne
    @JoinColumn(name = "id_tipo_residuo")
    private TipoResiduo tipoResiduo;

    @Column(name = "fecha_recoleccion")
    private LocalDate fechaRecoleccion;

    public RutaColonia() {
    }

    public RutaColonia(Ruta ruta, Colonia colonia) {
        this.ruta = ruta;
        this.colonia = colonia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }

    public TipoResiduo getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(TipoResiduo tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public LocalDate getFechaRecoleccion() {
        return fechaRecoleccion;
    }

    public void setFechaRecoleccion(LocalDate fechaRecoleccion) {
        this.fechaRecoleccion = fechaRecoleccion;
    }
}
