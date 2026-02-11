package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "ciudadano")
public class Ciudadano {

    @Id
    @Column(name = "id_persona")
    private Integer idPersona;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_persona")
    private Persona persona;

    @Column(name = "direccion_calle", nullable = false, length = 200)
    private String direccionCalle;

    @ManyToOne
    @JoinColumn(name = "id_colonia", nullable = false)
    private Colonia colonia;

    public Ciudadano() {
    }

    public Ciudadano(Colonia colonia, String direccionCalle, Integer idPersona, Persona persona) {
        this.colonia = colonia;
        this.direccionCalle = direccionCalle;
        this.idPersona = idPersona;
        this.persona = persona;
    }

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }

    public String getDireccionCalle() {
        return direccionCalle;
    }

    public void setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
