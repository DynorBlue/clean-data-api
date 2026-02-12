package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "conductor")
public class Conductor {

    @Id
    @Column(name = "id_persona")
    private Integer idPersona;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @Column(nullable = false, length = 50)
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

    public Conductor(EstadoOperativo estadoOperativo, LocalDate fechaAlta, LocalDate fechaBaja, Integer idPersona, String licencia, Persona persona) {
        this.estadoOperativo = estadoOperativo;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.idPersona = idPersona;
        this.licencia = licencia;
        this.persona = persona;
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

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
