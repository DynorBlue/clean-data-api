package utl.org.ldsm504.sakura.CleanDataApi.dto;

import java.util.List;

public class RutaDependenciasDTO {
    private Integer idRuta;
    private String nombreRuta;
    private Boolean activa;
    private int cantidadColonias;
    private int cantidadViajes;
    private int cantidadViajesActivos;
    private boolean puedeEliminarse;
    private String mensaje;

    public RutaDependenciasDTO() {
    }

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    public int getCantidadColonias() {
        return cantidadColonias;
    }

    public void setCantidadColonias(int cantidadColonias) {
        this.cantidadColonias = cantidadColonias;
    }

    public int getCantidadViajes() {
        return cantidadViajes;
    }

    public void setCantidadViajes(int cantidadViajes) {
        this.cantidadViajes = cantidadViajes;
    }

    public int getCantidadViajesActivos() {
        return cantidadViajesActivos;
    }

    public void setCantidadViajesActivos(int cantidadViajesActivos) {
        this.cantidadViajesActivos = cantidadViajesActivos;
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
