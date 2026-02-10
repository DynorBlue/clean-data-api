package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "tipo_residuo")
public class TipoResiduo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer idTipo;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    public TipoResiduo() {
    }

    public TipoResiduo(Integer idTipo, String nombre) {
        this.idTipo = idTipo;
        this.nombre = nombre;
    }

    public Integer getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Integer idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

