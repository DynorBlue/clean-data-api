package utl.org.ldsm504.sakura.CleanDataApi.dto;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoOperativo;

import java.time.LocalDate;

public class ConductorDTO {
    private Integer idPersona;
    private String licencia;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private EstadoOperativo estadoOperativo;
    private PersonaDTO persona;

    public ConductorDTO() {
    }

    public ConductorDTO(Integer idPersona, String licencia, LocalDate fechaAlta, LocalDate fechaBaja, EstadoOperativo estadoOperativo, PersonaDTO persona) {
        this.idPersona = idPersona;
        this.licencia = licencia;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estadoOperativo = estadoOperativo;
        this.persona = persona;
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

    public EstadoOperativo getEstadoOperativo() {
        return estadoOperativo;
    }

    public void setEstadoOperativo(EstadoOperativo estadoOperativo) {
        this.estadoOperativo = estadoOperativo;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }
}
