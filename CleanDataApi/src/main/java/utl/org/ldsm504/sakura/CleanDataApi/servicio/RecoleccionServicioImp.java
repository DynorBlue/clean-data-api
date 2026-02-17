package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Recoleccion;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.RecoleccionRepositorio;

import java.util.List;

@Service
public class RecoleccionServicioImp implements RecoleccionServicio {

    private final RecoleccionRepositorio recoleccionRepositorio;

    public RecoleccionServicioImp(RecoleccionRepositorio recoleccionRepositorio) {
        this.recoleccionRepositorio = recoleccionRepositorio;
    }

    @Override
    public Recoleccion crearRecoleccion(Recoleccion recoleccion) {
        return recoleccionRepositorio.save(recoleccion);
    }

    @Override
    public Recoleccion obtenerRecoleccionPorId(Integer id) {
        return recoleccionRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Recolección no encontrada con id " + id));
    }

    @Override
    public List<Recoleccion> obtenerTodasRecolecciones() {
        return recoleccionRepositorio.findAll();
    }

    @Transactional
    @Override
    public Recoleccion actualizarRecoleccion(Recoleccion recoleccion) {
        if (recoleccion.getIdRecoleccion() == null) {
            throw new RuntimeException("La recolección no tiene ID");
        }

        Recoleccion existente = obtenerRecoleccionPorId(recoleccion.getIdRecoleccion());
        existente.setViaje(recoleccion.getViaje());
        existente.setTipoResiduo(recoleccion.getTipoResiduo());
        existente.setVolumenM3(recoleccion.getVolumenM3());
        existente.setPesoKg(recoleccion.getPesoKg());

        return recoleccionRepositorio.save(existente);
    }

    @Override
    public void eliminarRecoleccion(Integer id) {
        if (!recoleccionRepositorio.existsById(id)) {
            throw new RuntimeException("No existe recolección con id " + id);
        }
        recoleccionRepositorio.deleteById(id);
    }

    @Override
    public List<Recoleccion> obtenerPorViaje(Integer idViaje) {
        return recoleccionRepositorio.findByViajeIdViaje(idViaje);
    }
}
