package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.dto.RutaDependenciasDTO;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoViaje;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Ruta;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.RutaColonia;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Viaje;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.RutaColoniaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.RutaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.ViajeRepositorio;

import java.time.LocalDate;
import java.util.List;

@Service
public class RutaServicioImp implements RutaServicio {

    private final RutaRepositorio rutaRepositorio;
    private final RutaColoniaRepositorio rutaColoniaRepositorio;
    private final ViajeRepositorio viajeRepositorio;

    public RutaServicioImp(RutaRepositorio rutaRepositorio, 
                           RutaColoniaRepositorio rutaColoniaRepositorio,
                           ViajeRepositorio viajeRepositorio) {
        this.rutaRepositorio = rutaRepositorio;
        this.rutaColoniaRepositorio = rutaColoniaRepositorio;
        this.viajeRepositorio = viajeRepositorio;
    }

    @Override
    public Ruta crearRuta(Ruta ruta) {
        return rutaRepositorio.save(ruta);
    }

    @Override
    public Ruta obtenerRutaPorId(Integer id) {
        return rutaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada con id " + id));
    }

    @Override
    public List<Ruta> obtenerTodosRutas() {
        return rutaRepositorio.findAll();
    }

    @Transactional
    @Override
    public Ruta actualizarRuta(Ruta ruta) {
        if (ruta.getIdRuta() == null) {
            throw new RuntimeException("la ruta no tiene ID");
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

    @Override
    public Ruta obtenerPorColoniaYFecha(Integer idColonia, LocalDate fecha) {
        return rutaColoniaRepositorio.findByColoniaIdColoniaAndFechaRecoleccion(idColonia, fecha)
                .map(RutaColonia::getRuta)
                .orElse(null);
    }

    @Override
    public RutaDependenciasDTO verificarDependencias(Integer id) {
        Ruta ruta = obtenerRutaPorId(id);

        List<RutaColonia> colonias = rutaColoniaRepositorio.findByRutaIdRuta(id);
        List<Viaje> viajes = viajeRepositorio.findByRutaIdRuta(id);
        List<Viaje> viajesActivos = viajes.stream()
                .filter(v -> v.getEstado() == EstadoViaje.EN_CURSO)
                .toList();

        StringBuilder mensajeError = new StringBuilder();

        if (!colonias.isEmpty()) {
            mensajeError.append("- Tiene ").append(colonias.size())
                   .append(" colonia(s) asignada(s)\n");
            mensajeError.append("  Primero desasocie las colonias de la ruta\n");
        }

        if (!viajesActivos.isEmpty()) {
            mensajeError.append("- Tiene ").append(viajesActivos.size())
                   .append(" viaje(s) en curso\n");
            mensajeError.append("  Espere a que finalicen o cancele los viajes\n");
        }

        boolean puedeEliminarse = colonias.isEmpty() && viajesActivos.isEmpty();

        String mensaje = puedeEliminarse 
                ? "No tiene dependencias activas. Puede eliminarse."
                : mensajeError.toString().trim();

        RutaDependenciasDTO dto = new RutaDependenciasDTO();
        dto.setIdRuta(ruta.getIdRuta());
        dto.setNombreRuta(ruta.getNombre());
        dto.setActiva(ruta.getActiva());
        dto.setCantidadColonias(colonias.size());
        dto.setCantidadViajes(viajes.size());
        dto.setCantidadViajesActivos(viajesActivos.size());
        dto.setPuedeEliminarse(puedeEliminarse);
        dto.setMensaje(mensaje);

        return dto;
    }

    @Override
    public boolean puedeEliminarse(Integer id) {
        List<RutaColonia> colonias = rutaColoniaRepositorio.findByRutaIdRuta(id);
        List<Viaje> viajesActivos = viajeRepositorio.findByRutaIdRuta(id).stream()
                .filter(v -> v.getEstado() == EstadoViaje.EN_CURSO)
                .toList();

        return colonias.isEmpty() && viajesActivos.isEmpty();
    }
}
