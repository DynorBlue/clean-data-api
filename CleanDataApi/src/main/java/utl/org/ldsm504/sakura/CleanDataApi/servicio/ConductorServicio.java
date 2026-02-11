package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.Conductor;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Persona;

public interface ConductorServicio {
    Conductor registrarConductor(Persona persona, Conductor conductor);

}
