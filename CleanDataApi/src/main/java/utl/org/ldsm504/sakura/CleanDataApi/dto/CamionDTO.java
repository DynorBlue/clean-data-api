package utl.org.ldsm504.sakura.CleanDataApi.dto;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoCamion;

public class CamionDTO {
    private Integer idCamion;
    private String placas;
    private String modelo;
    private Integer capacidadKg;
    private Integer capacidadM3;
    private EstadoCamion estado;

    public CamionDTO() {
    }

    public CamionDTO(Integer idCamion, String placas, String modelo, Integer capacidadKg, Integer capacidadM3, EstadoCamion estado) {
        this.idCamion = idCamion;
        this.placas = placas;
        this.modelo = modelo;
        this.capacidadKg = capacidadKg;
        this.capacidadM3 = capacidadM3;
        this.estado = estado;
    }

    public Integer getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(Integer idCamion) {
        this.idCamion = idCamion;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getCapacidadKg() {
        return capacidadKg;
    }

    public void setCapacidadKg(Integer capacidadKg) {
        this.capacidadKg = capacidadKg;
    }

    public Integer getCapacidadM3() {
        return capacidadM3;
    }

    public void setCapacidadM3(Integer capacidadM3) {
        this.capacidadM3 = capacidadM3;
    }

    public EstadoCamion getEstado() {
        return estado;
    }

    public void setEstado(EstadoCamion estado) {
        this.estado = estado;
    }
}
