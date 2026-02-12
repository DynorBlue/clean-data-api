package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(length = 20)
    private String telefono;

    @OneToOne
    @JoinColumn(name = "id_usuario", unique = true)
    private      Usuario usuario;

    public Persona() {
    }

    public Persona(Integer idPersona, String nombre, String telefono, Usuario usuario) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.telefono = telefono;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
