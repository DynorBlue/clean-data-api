package utl.org.ldsm504.sakura.CleanDataApi.servicio;


import utl.org.ldsm504.sakura.CleanDataApi.modelo.TipoResiduo;

import java.util.List;

public interface TipoResiduoServicio {
    TipoResiduo crearTipoResiduo(TipoResiduo tipoResiduo);
    TipoResiduo obtenerTipoResiduoPorId(Integer id);
    List<TipoResiduo> obtenerTodosTiposResiduos();
    TipoResiduo actualizarColonia(TipoResiduo tipoResiduo);
    void eliminarTipoResiduo(Integer id);

}
