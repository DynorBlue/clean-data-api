package utl.org.ldsm504.sakura.CleanDataApi.dto;

public class TipoResiduoDTO {
    private Integer idTipo;
    private String nombre;

    public TipoResiduoDTO() {
    }

    public TipoResiduoDTO(Integer idTipo, String nombre) {
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
