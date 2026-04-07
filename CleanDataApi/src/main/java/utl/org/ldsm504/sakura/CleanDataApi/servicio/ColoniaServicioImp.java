package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.dto.ColoniaDependenciasDTO;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Colonia;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.CiudadanoRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.ColoniaRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.ReporteRepositorio;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.RutaColoniaRepositorio;

import java.util.List;

@Service
public class ColoniaServicioImp implements ColoniaServicio {

    private final ColoniaRepositorio coloniaRepositorio;
    private final RutaColoniaRepositorio rutaColoniaRepositorio;
    private final CiudadanoRepositorio ciudadanoRepositorio;
    private final ReporteRepositorio reporteRepositorio;

    public ColoniaServicioImp(ColoniaRepositorio coloniaRepositorio,
                             RutaColoniaRepositorio rutaColoniaRepositorio,
                             CiudadanoRepositorio ciudadanoRepositorio,
                             ReporteRepositorio reporteRepositorio) {
        this.coloniaRepositorio = coloniaRepositorio;
        this.rutaColoniaRepositorio = rutaColoniaRepositorio;
        this.ciudadanoRepositorio = ciudadanoRepositorio;
        this.reporteRepositorio = reporteRepositorio;
    }

    @Override
    public Colonia crearColonia(Colonia colonia) {
        return coloniaRepositorio.save(colonia);
    }

    @Override
    public Colonia obtenerColoniaPorId(Integer id) {
        return coloniaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Colonia no encontrada con id " + id));
    }

    @Override
    public List<Colonia> obtenerTodasColonia() {
        return coloniaRepositorio.findAll();
    }

    @Transactional
    @Override
    public Colonia actualizarColonia(Colonia colonia) {
        if (colonia.getIdColonia() == null) {
            throw new RuntimeException("La colonia no tiene ID");
        }

        Colonia existente = obtenerColoniaPorId(colonia.getIdColonia());

        existente.setNombre(colonia.getNombre());
        existente.setCodigoPostal(colonia.getCodigoPostal());
        existente.setLatitud(colonia.getLatitud());
        existente.setLongitud(colonia.getLongitud());
        return coloniaRepositorio.save(colonia);
    }

    @Override
    public void eliminarColonia(Integer id) {
        if (!coloniaRepositorio.existsById(id)) {
            throw new RuntimeException("No existe colonia con id " + id);
        }
        coloniaRepositorio.deleteById(id);
    }

    @Transactional
    @Override
    public Colonia actualizarColoniaPorId(Integer id, Colonia datos) {
        Colonia existente = obtenerColoniaPorId(id);

        if (datos.getNombre() != null)
            existente.setNombre(datos.getNombre());

        if (datos.getCodigoPostal() != null)
            existente.setCodigoPostal(datos.getCodigoPostal());

        if (datos.getLatitud() != null)
            existente.setLatitud(datos.getLatitud());

        if (datos.getLongitud() != null)
            existente.setLongitud(datos.getLongitud());

        return coloniaRepositorio.save(existente);
    }

    @Override
    public Colonia buscarPorNombreExacto(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la colonia no puede estar vacio.");
        }

        return coloniaRepositorio.findByNombreIgnoreCase(nombre)
                .orElseThrow(() -> new RuntimeException("No se encontro la colonia con el nombre: " + nombre));
    }

    @Override
    public List<Colonia> buscarPorNombreContiene(String parteNombre) {
        return coloniaRepositorio.findByNombreContainingIgnoreCase(parteNombre);
    }

    @Override
    public List<Colonia> buscarPorCodigoPostal(String cp) {
        return coloniaRepositorio.findByCodigoPostal(cp);
    }

    @Override
    public ColoniaDependenciasDTO verificarDependencias(Integer id) {
        Colonia colonia = obtenerColoniaPorId(id);

        int cantidadRutas = rutaColoniaRepositorio.findByColoniaIdColonia(id).size();
        int cantidadCiudadanos = (int) ciudadanoRepositorio.countByColoniaIdColonia(id);
        int cantidadReportes = (int) reporteRepositorio.countByColoniaIdColonia(id);

        StringBuilder mensajeError = new StringBuilder();

        if (cantidadRutas > 0) {
            mensajeError.append("- Esta asociada a ").append(cantidadRutas)
                   .append(" ruta(s)\n");
        }
        if (cantidadCiudadanos > 0) {
            mensajeError.append("- Tiene ").append(cantidadCiudadanos)
                   .append(" ciudadano(s) registrados\n");
        }
        if (cantidadReportes > 0) {
            mensajeError.append("- Tiene ").append(cantidadReportes)
                   .append(" reporte(s) asociados\n");
        }

        boolean puedeEliminarse = cantidadRutas == 0 && cantidadCiudadanos == 0 && cantidadReportes == 0;

        String mensaje = puedeEliminarse
                ? "No tiene dependencias. Puede eliminarse."
                : mensajeError.toString().trim();

        ColoniaDependenciasDTO dto = new ColoniaDependenciasDTO();
        dto.setIdColonia(colonia.getIdColonia());
        dto.setNombreColonia(colonia.getNombre());
        dto.setCantidadRutas(cantidadRutas);
        dto.setCantidadCiudadanos(cantidadCiudadanos);
        dto.setCantidadReportes(cantidadReportes);
        dto.setPuedeEliminarse(puedeEliminarse);
        dto.setMensaje(mensaje);

        return dto;
    }

    @Override
    public boolean puedeEliminarse(Integer id) {
        int cantidadRutas = rutaColoniaRepositorio.findByColoniaIdColonia(id).size();
        int cantidadCiudadanos = (int) ciudadanoRepositorio.countByColoniaIdColonia(id);
        int cantidadReportes = (int) reporteRepositorio.countByColoniaIdColonia(id);

        return cantidadRutas == 0 && cantidadCiudadanos == 0 && cantidadReportes == 0;
    }
}
