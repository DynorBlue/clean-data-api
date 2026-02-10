package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import java.time.LocalDateTime;

public class Reporte {
    private Long idReporte;
    private Usuario usuario;
    private Colonia colonia;
    private TipoResiduo tipoResiduo;
    private String descripcion;
    private LocalDateTime fecha;
    private EstadoReporte estado;
}
