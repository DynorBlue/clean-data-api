package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Camion;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Ruta;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.RutaRepositorio;

import java.util.List;
@Service
public class RutaServicioImp implements RutaServicio{

    private final RutaRepositorio rutaRepositorio;

    public RutaServicioImp(RutaRepositorio rutaRepositorio) {
        this.rutaRepositorio = rutaRepositorio;
    }


    @Override
    public Ruta crearRuta(Ruta ruta) {
        return rutaRepositorio.save(ruta);
    }

    @Override
    public Ruta obtenerRutaPorId(Integer id) {
        return rutaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Colonia no encontrada con id " + id));
    }

    @Override
    public List<Ruta> obtenerTodosRutas() {
        return rutaRepositorio.findAll();
    }
    @Transactional
    @Override
    public Ruta actualizarRuta(Ruta ruta) {
        if (ruta.getIdRuta() == null) {
            throw new RuntimeException("la ruta  no tiene ID");
        }

        Ruta existente = obtenerRutaPorId(ruta.getIdRuta());

        existente.setNombre(ruta.getNombre());
        existente.setDescripcion(ruta.getDescripcion());
        existente.setActiva(ruta.getActiva());

        return rutaRepositorio.save(existente);
    }

    @Override
    public void eliminarRuta(Integer id) {
        if (!rutaRepositorio.existsById(id)) {
            throw new RuntimeException("No existe ruta con id " + id);
        }
        rutaRepositorio.deleteById(id);
    }

    @Override
    public List<Ruta> buscarPorNombreContiene(String parteNombre) {
        return rutaRepositorio.findByNombreContainingIgnoreCase(parteNombre);
    }

    @Override
    public List<Ruta> bucarPorEstadoActivo() {
        return rutaRepositorio.findByActivaTrue();
    }
}
