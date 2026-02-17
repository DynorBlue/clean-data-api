package utl.org.ldsm504.sakura.CleanDataApi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoViaje;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Viaje;

import java.util.List;

public interface ViajeRepositorio extends JpaRepository<Viaje, Integer> {
    List<Viaje> findByEstado(EstadoViaje estado);
    List<Viaje> findByConductorIdPersona(Integer idConductor);
    List<Viaje> findByCamionIdCamion(Integer idCamion);

    @Query("SELECT v FROM Viaje v JOIN v.ruta r JOIN r.colonias rc WHERE rc.colonia.idColonia = :idColonia")
    List<Viaje> findByColoniaIdColonia(@Param("idColonia") Integer idColonia);
}
