package utl.org.ldsm504.sakura.CleanDataApi.dto;

public class RutaDTO {
    private Integer idRuta;
    private String nombre;
    private String descripcion;
    private Boolean activa;

    public RutaDTO() {
    }

    public RutaDTO(Integer idRuta, String nombre, String descripcion, Boolean activa) {
        this.idRuta = idRuta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activa = activa;
    }

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }
}
