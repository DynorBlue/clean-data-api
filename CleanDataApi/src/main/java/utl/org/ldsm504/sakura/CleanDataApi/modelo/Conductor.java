package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Conductor extends Usuario {
    private String licencia;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private EstadoOperativo estadoOperativo;
    private BigDecimal calificacion;
}
