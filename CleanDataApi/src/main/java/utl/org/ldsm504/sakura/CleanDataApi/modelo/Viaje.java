package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import java.time.LocalDateTime;

public class Viaje {
    private Long idViaje;
    private Camion camion;
    private Conductor conductor;
    private Ruta ruta;
    private TipoResiduo tipoResiduo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private EstadoViaje estado;
}
