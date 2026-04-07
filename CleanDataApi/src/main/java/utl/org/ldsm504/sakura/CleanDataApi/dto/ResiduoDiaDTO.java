package utl.org.ldsm504.sakura.CleanDataApi.dto;

import java.time.LocalDate;

public class ResiduoDiaDTO {
    private LocalDate fecha;
    private String tipoResiduo;
    private String nombreRuta;
    private String nombreColonia;

    public ResiduoDiaDTO() {
    }

    public ResiduoDiaDTO(LocalDate fecha, String tipoResiduo, String nombreRuta, String nombreColonia) {
        this.fecha = fecha;
        this.tipoResiduo = tipoResiduo;
        this.nombreRuta = nombreRuta;
        this.nombreColonia = nombreColonia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(String tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public String getNombreColonia() {
        return nombreColonia;
    }

    public void setNombreColonia(String nombreColonia) {
        this.nombreColonia = nombreColonia;
    }
}
