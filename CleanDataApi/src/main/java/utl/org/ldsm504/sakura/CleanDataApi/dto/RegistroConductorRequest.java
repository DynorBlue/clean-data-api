package utl.org.ldsm504.sakura.CleanDataApi.dto;

import java.time.LocalDate;

public class RegistroConductorRequest {
    private String email;
    private String password;

    private String nombre;
    private String telefono;

    private Integer idColonia;
    private String direccionCalle;

    private String licencia;
    private LocalDate fechaAlta;

    // getters y setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Integer getIdColonia() { return idColonia; }
    public void setIdColonia(Integer idColonia) { this.idColonia = idColonia; }

    public String getDireccionCalle() { return direccionCalle; }
    public void setDireccionCalle(String direccionCalle) { this.direccionCalle = direccionCalle; }

    public String getLicencia() { return licencia; }
    public void setLicencia(String licencia) { this.licencia = licencia; }

    public LocalDate getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }
}