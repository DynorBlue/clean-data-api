package utl.org.ldsm504.sakura.CleanDataApi.dto;

public class PersonaDTO {

    private Integer idPersona;
    private String nombre;
    private String telefono;
    private String email;   // ← nuevo campo

    public PersonaDTO() {
    }
    public PersonaDTO(Integer idPersona, String nombre, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public PersonaDTO(Integer idPersona, String nombre, String telefono, String email) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {        // ← importante para que Jackson lo serialice
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}