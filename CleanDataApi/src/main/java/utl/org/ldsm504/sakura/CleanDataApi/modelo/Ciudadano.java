package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import jakarta.persistence.*;

@Entity

@Table(name = "ciudadano")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Ciudadano extends Usuario {

    @Column(name = "direccion_calle", nullable = false, length = 200)
    private String direccionCalle;

    @ManyToOne
    @JoinColumn(name = "id_colonia", nullable = false)
    private Colonia colonia;

    public Ciudadano() {
    }

    public Ciudadano(Colonia colonia, String direccionCalle) {
        this.colonia = colonia;
        this.direccionCalle = direccionCalle;
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
}
