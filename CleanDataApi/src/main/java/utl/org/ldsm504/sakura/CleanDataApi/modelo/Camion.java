package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "camion")
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_camion")
    private Integer idCamion;

    @Column(name = "placas", unique = true, length = 20)
    private String placas;

    @Column(name = "modelo", length = 100)
    private String modelo;

    @Column(name = "capacidad_kg")
    private Integer capacidadKg;

    @Column(name = "capacidad_m3")
    private Integer capacidadM3;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoCamion estado;

    public Camion() {
    }

    public Camion(Integer capacidadKg, Integer capacidadM3, EstadoCamion estado, Integer idCamion, String modelo, String placas) {
        this.capacidadKg = capacidadKg;
        this.capacidadM3 = capacidadM3;
        this.estado = estado;
        this.idCamion = idCamion;
        this.modelo = modelo;
        this.placas = placas;
    }

    public Integer getCapacidadKg() {
        return capacidadKg;
    }

    public void setCapacidadKg(Integer capacidadKg) {
        this.capacidadKg = capacidadKg;
    }

    public Integer getCapacidadM3() {
        return capacidadM3;
    }

    public void setCapacidadM3(Integer capacidadM3) {
        this.capacidadM3 = capacidadM3;
    }

    public EstadoCamion getEstado() {
        return estado;
    }

    public void setEstado(EstadoCamion estado) {
        this.estado = estado;
    }

    public Integer getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Integer idCamion) {
        this.idCamion = idCamion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }
}

