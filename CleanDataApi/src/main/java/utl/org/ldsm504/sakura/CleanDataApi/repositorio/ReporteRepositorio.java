package utl.org.ldsm504.sakura.CleanDataApi.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoReporte;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Reporte;

import java.util.List;

public interface ReporteRepositorio extends JpaRepository<Reporte, Integer> {
    List<Reporte> findByEstado(EstadoReporte estado);
    List<Reporte> findByUsuarioIdUsuario(Integer idUsuario);
    List<Reporte> findByColoniaIdColonia(Integer idColonia);
}
