package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import utl.org.ldsm504.sakura.CleanDataApi.modelo.Camion;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.TipoResiduo;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.TipoResiduoRepositorio;

import java.util.List;

public class TipoResiduoImp implements TipoResiduoServicio{
    private final TipoResiduoRepositorio tipoResiduoRepositorio;

    public TipoResiduoImp(TipoResiduoRepositorio tipoResiduoRepositorio) {
        this.tipoResiduoRepositorio = tipoResiduoRepositorio;
    }

    @Override
    public TipoResiduo crearTipoResiduo(TipoResiduo tipoResiduo) {
        return tipoResiduoRepositorio.save(tipoResiduo);
    }

    @Override
    public TipoResiduo obtenerTipoResiduoPorId(Integer id) {
        return tipoResiduoRepositorio.
                findById(id).orElseThrow(() -> new RuntimeException("Tipo residuo no encontrado con id " + id));
    }

    @Override
    public List<TipoResiduo> obtenerTodosTiposResiduos() {
        return tipoResiduoRepositorio.findAll();
    }

    @Override
    public TipoResiduo actualizarColonia(TipoResiduo tipoResiduo) {
        if (tipoResiduo.getIdTipo() == null) {
            throw new RuntimeException("El tipo residuo no tiene ID");
        }

        TipoResiduo existente = obtenerTipoResiduoPorId(tipoResiduo.getIdTipo());

        existente.setNombre(tipoResiduo.getNombre());

        return tipoResiduoRepositorio.save(existente);
    }

    @Override
    public void eliminarTipoResiduo(Integer id) {
        if (!tipoResiduoRepositorio.existsById(id)) {
            throw new RuntimeException("No existe el tipo de residuo con el id " + id);
        }
        tipoResiduoRepositorio.deleteById(id);

    }
}
