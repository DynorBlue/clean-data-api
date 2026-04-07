package utl.org.ldsm504.sakura.CleanDataApi.dto;

import java.time.LocalDate;

public class RutaColoniaDTO {
    private Integer id;
    private RutaDTO ruta;
    private ColoniaDTO colonia;
    private TipoResiduoDTO tipoResiduo;
    private LocalDate fechaRecoleccion;

    public RutaColoniaDTO() {
    }

    public RutaColoniaDTO(Integer id, RutaDTO ruta, ColoniaDTO colonia, TipoResiduoDTO tipoResiduo, LocalDate fechaRecoleccion) {
        this.id = id;
        this.ruta = ruta;
        this.colonia = colonia;
        this.tipoResiduo = tipoResiduo;
        this.fechaRecoleccion = fechaRecoleccion;
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

    public TipoResiduoDTO getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(TipoResiduoDTO tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public LocalDate getFechaRecoleccion() {
        return fechaRecoleccion;
    }

    public void setFechaRecoleccion(LocalDate fechaRecoleccion) {
        this.fechaRecoleccion = fechaRecoleccion;
    }
}
