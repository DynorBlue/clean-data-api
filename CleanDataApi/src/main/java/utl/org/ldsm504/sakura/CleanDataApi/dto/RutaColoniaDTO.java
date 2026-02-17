package utl.org.ldsm504.sakura.CleanDataApi.dto;

public class RutaColoniaDTO {
    private Integer id;
    private RutaDTO ruta;
    private ColoniaDTO colonia;

    public RutaColoniaDTO() {
    }

    public RutaColoniaDTO(Integer id, RutaDTO ruta, ColoniaDTO colonia) {
        this.id = id;
        this.ruta = ruta;
        this.colonia = colonia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RutaDTO getRuta() {
        return ruta;
    }

    public void setRuta(RutaDTO ruta) {
        this.ruta = ruta;
    }

    public ColoniaDTO getColonia() {
        return colonia;
    }

    public void setColonia(ColoniaDTO colonia) {
        this.colonia = colonia;
    }
}
