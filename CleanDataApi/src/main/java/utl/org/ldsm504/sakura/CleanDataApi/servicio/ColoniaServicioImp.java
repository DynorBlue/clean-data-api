package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.Camion;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Colonia;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.ColoniaRepositorio;

import java.util.List;

public class ColoniaServicioImp implements ColoniaServicio{

    private final ColoniaRepositorio coloniaRepositorio;

    public ColoniaServicioImp(ColoniaRepositorio coloniaRepositorio) {
        this.coloniaRepositorio = coloniaRepositorio;
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

    @Override
    public Colonia actualizarColonia(Colonia colonia) {
        if (colonia.getIdColonia() == null) {
            throw new RuntimeException("la colonia no tiene ID");
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

    @Override
    public Colonia actualizarColoniaPorId(Integer id, Colonia datos) {
        Colonia existente = obtenerColoniaPorId(id);

        if (datos.getNombre() != null)
            existente.setNombre(datos.getNombre());

        if (datos.getCodigoPostal() != null)
            existente.setCodigoPostal(datos.getCodigoPostal());

        if (datos.getLatitud() != null)
            existente.setLatitud(datos.getLongitud());

        if (datos.getLongitud() != null)
            existente.setLongitud(datos.getLongitud());


        return coloniaRepositorio.save(existente);
    }

    @Override
    public Colonia buscarPorNombreExacto(String nombre) {
        // 1. Validar que el nombre no sea nulo ni esté vacío
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la colonia no puede estar vacío.");
        }

        // 2. Llamar al repositorio y manejar el Optional
        return coloniaRepositorio.findByNombreIgnoreCase(nombre)
                .orElseThrow(() -> new RuntimeException("No se encontró la colonia con el nombre: " + nombre));
    }

    @Override
    public List<Colonia> buscarPorNombreContiene(String parteNombre) {
        return coloniaRepositorio.findByNombreContainingIgnoreCase(parteNombre);
    }
}
