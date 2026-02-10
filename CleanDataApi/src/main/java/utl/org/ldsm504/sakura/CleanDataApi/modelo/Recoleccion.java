package utl.org.ldsm504.sakura.CleanDataApi.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Recoleccion {
    private Long idRecoleccion;
    private Viaje viaje;
    private TipoResiduo tipoResiduo;
    private BigDecimal volumenM3;
    private BigDecimal pesoKg;
    private LocalDateTime fechaRegistro;
}
