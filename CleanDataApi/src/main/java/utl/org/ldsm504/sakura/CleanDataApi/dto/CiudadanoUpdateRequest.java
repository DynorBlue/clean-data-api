package utl.org.ldsm504.sakura.CleanDataApi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CiudadanoUpdateRequest {

    @Size(max = 120)
    private String nombre;

    @Size(max = 20)
    private String telefono;

    @Size(max = 200)
    private String direccionCalle;

    @NotNull(message = "La colonia es requerida")
    private Integer idColonia;

    public CiudadanoUpdateRequest() {
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

    public String getDireccionCalle() {
        return direccionCalle;
    }

    public void setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
    }

    public Integer getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(Integer idColonia) {
        this.idColonia = idColonia;
    }
}
