package utl.org.ldsm504.sakura.CleanDataApi.dto;

public class CiudadanoDTO {
    private Integer idPersona;
    private String direccionCalle;
    private ColoniaDTO colonia;
    private PersonaDTO persona;

    public CiudadanoDTO() {
    }

    public CiudadanoDTO(Integer idPersona, String direccionCalle, ColoniaDTO colonia, PersonaDTO persona) {
        this.idPersona = idPersona;
        this.direccionCalle = direccionCalle;
        this.colonia = colonia;
        this.persona = persona;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getDireccionCalle() {
        return direccionCalle;
    }

    public void setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
    }

    public ColoniaDTO getColonia() {
        return colonia;
    }

    public void setColonia(ColoniaDTO colonia) {
        this.colonia = colonia;
    }

    public PersonaDTO getPersona() {
        return persona;
    }

    public void setPersona(PersonaDTO persona) {
        this.persona = persona;
    }
}
