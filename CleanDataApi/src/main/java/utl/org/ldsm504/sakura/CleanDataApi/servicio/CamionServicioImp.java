package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Camion;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoCamion;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.CamionRepositorio;

import java.util.List;
@Service
public class CamionServicioImp implements CamionServicio{

    private final CamionRepositorio camionRepositorio;

    public CamionServicioImp(CamionRepositorio camionRepositorio) {
        this.camionRepositorio = camionRepositorio;
    }

    @Override
    public Camion crearCamion(Camion camion) {
        return camionRepositorio.save(camion);
    }

    @Override
    public Camion obtenerCamionPorId(Integer id) {
        return camionRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Camión no encontrado con id " + id));
    }

    @Override
    public List<Camion> obtenerTodosCamiones() {
        return camionRepositorio.findAll();
    }

    @Transactional
    @Override
    public Camion actualizarCamion(Camion camion) {
        if (camion.getIdCamion() == null) {
            throw new RuntimeException("El camión no tiene ID");
        }

        Camion existente = obtenerCamionPorId(camion.getIdCamion());

        existente.setPlacas(camion.getPlacas());
        existente.setModelo(camion.getModelo());
        existente.setCapacidadKg(camion.getCapacidadKg());
        existente.setCapacidadM3(camion.getCapacidadM3());
        existente.setEstado(camion.getEstado());

        return camionRepositorio.save(existente);
    }

    @Override
    public void eliminarCamion(Integer id) {
        if (!camionRepositorio.existsById(id)) {
                throw new RuntimeException("No existe camión con id " + id);
            }
            camionRepositorio.deleteById(id);
    }

    @Override
    public List<Camion> obtenerCamionPorEstado(EstadoCamion estadoCamion) {
        return camionRepositorio.findByEstado(estadoCamion);
    }

    @Transactional
    @Override
    public Camion actualizarCamionPorId(Integer id, Camion datos) {
        Camion existente = obtenerCamionPorId(id);

        if (datos.getPlacas() != null)
            existente.setPlacas(datos.getPlacas());

        if (datos.getModelo() != null)
            existente.setModelo(datos.getModelo());

        if (datos.getCapacidadKg() != null)
            existente.setCapacidadKg(datos.getCapacidadKg());

        if (datos.getCapacidadM3() != null)
            existente.setCapacidadM3(datos.getCapacidadM3());

        if (datos.getEstado() != null)
            existente.setEstado(datos.getEstado());

        return camionRepositorio.save(existente);
    }
}
