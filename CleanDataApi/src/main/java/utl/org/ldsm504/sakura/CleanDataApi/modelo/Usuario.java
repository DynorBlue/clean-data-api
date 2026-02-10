package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;


@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    private int idUsuario;
    @Column(name = "nombre",)
    private String nombre;
    private String email;
    private String contrasena;
    private String telefono;
    private TipoUsuario tipoUsuario;
    private Boolean activo;
    private LocalDateTime fechaRegistro;
}
