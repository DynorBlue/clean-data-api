package utl.org.ldsm504.sakura.CleanDataApi.dto;

public class ColoniaDependenciasDTO {
    private Integer idColonia;
    private String nombreColonia;
    private int cantidadRutas;
    private int cantidadCiudadanos;
    private int cantidadReportes;
    private boolean puedeEliminarse;
    private String mensaje;

    public ColoniaDependenciasDTO() {
    }

    public Integer getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(Integer idColonia) {
        this.idColonia = idColonia;
    }

    public String getNombreColonia() {
        return nombreColonia;
    }

    public void setNombreColonia(String nombreColonia) {
        this.nombreColonia = nombreColonia;
    }

    public int getCantidadRutas() {
        return cantidadRutas;
    }

    public void setCantidadRutas(int cantidadRutas) {
        this.cantidadRutas = cantidadRutas;
    }

    public int getCantidadCiudadanos() {
        return cantidadCiudadanos;
    }

    public void setCantidadCiudadanos(int cantidadCiudadanos) {
        this.cantidadCiudadanos = cantidadCiudadanos;
    }

    public int getCantidadReportes() {
        return cantidadReportes;
    }

    public void setCantidadReportes(int cantidadReportes) {
        this.cantidadReportes = cantidadReportes;
    }

    public boolean isPuedeEliminarse() {
        return puedeEliminarse;
    }

    public void setPuedeEliminarse(boolean puedeEliminarse) {
        this.puedeEliminarse = puedeEliminarse;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
