package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "conductor")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Conductor extends Usuario {

    @Column(name = "licencia", nullable = false, length = 50)
    private String licencia;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;

    @Column(name = "fecha_baja")
    private LocalDate fechaBaja;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_operativo", nullable = false)
    private EstadoOperativo estadoOperativo;

    public Conductor() {
    }

    public Conductor(EstadoOperativo estadoOperativo, LocalDate fechaAlta, LocalDate fechaBaja, String licencia) {
        this.estadoOperativo = estadoOperativo;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.licencia = licencia;
    }

    public EstadoOperativo getEstadoOperativo() {
        return estadoOperativo;
    }

    public void setEstadoOperativo(EstadoOperativo estadoOperativo) {
        this.estadoOperativo = estadoOperativo;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }
}

