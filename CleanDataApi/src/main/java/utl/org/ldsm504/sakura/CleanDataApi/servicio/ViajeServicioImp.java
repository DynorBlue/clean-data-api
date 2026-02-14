package utl.org.ldsm504.sakura.CleanDataApi.servicio;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.EstadoViaje;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Viaje;
import utl.org.ldsm504.sakura.CleanDataApi.repositorio.ViajeRepositorio;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ViajeServicioImp implements ViajeServicio {

    private final ViajeRepositorio viajeRepositorio;

    public ViajeServicioImp(ViajeRepositorio viajeRepositorio) {
        this.viajeRepositorio = viajeRepositorio;
    }

    @Override
    public Viaje crearViaje(Viaje viaje) {
        return viajeRepositorio.save(viaje);
    }

    @Override
    public Viaje obtenerViajePorId(Integer id) {
        return viajeRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado con id " + id));
    }

    @Override
    public List<Viaje> obtenerTodosViajes() {
        return viajeRepositorio.findAll();
    }

    @Transactional
    @Override
    public Viaje actualizarViaje(Viaje viaje) {
        if (viaje.getIdViaje() == null) {
            throw new RuntimeException("El viaje no tiene ID");
        }

        Viaje existente = obtenerViajePorId(viaje.getIdViaje());
        existente.setCamion(viaje.getCamion());
        existente.setConductor(viaje.getConductor());
        existente.setRuta(viaje.getRuta());
        existente.setTipoResiduo(viaje.getTipoResiduo());
        existente.setFechaInicio(viaje.getFechaInicio());
        existente.setFechaFin(viaje.getFechaFin());
        existente.setEstado(viaje.getEstado());

        return viajeRepositorio.save(existente);
    }

    @Override
    public void eliminarViaje(Integer id) {
        if (!viajeRepositorio.existsById(id)) {
            throw new RuntimeException("No existe viaje con id " + id);
        }
        viajeRepositorio.deleteById(id);
    }

    @Override
    public List<Viaje> obtenerPorEstado(EstadoViaje estado) {
        return viajeRepositorio.findByEstado(estado);
    }

    @Override
    public List<Viaje> obtenerPorConductor(Integer idConductor) {
        return viajeRepositorio.findByConductorIdPersona(idConductor);
    }

    @Transactional
    @Override
    public Viaje iniciarViaje(Integer id) {
        Viaje viaje = obtenerViajePorId(id);
        viaje.setFechaInicio(LocalDateTime.now());
        viaje.setEstado(EstadoViaje.EN_CURSO);
        return viajeRepositorio.save(viaje);
    }

    @Transactional
    @Override
    public Viaje finalizarViaje(Integer id) {
        Viaje viaje = obtenerViajePorId(id);
        viaje.setFechaFin(LocalDateTime.now());
        viaje.setEstado(EstadoViaje.FINALIZADO);
        return viajeRepositorio.save(viaje);
    }
}
