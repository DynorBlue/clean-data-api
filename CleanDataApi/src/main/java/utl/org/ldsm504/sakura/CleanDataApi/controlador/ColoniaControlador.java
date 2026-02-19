package utl.org.ldsm504.sakura.CleanDataApi.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import utl.org.ldsm504.sakura.CleanDataApi.dto.ColoniaDTO;
import utl.org.ldsm504.sakura.CleanDataApi.modelo.Colonia;
import utl.org.ldsm504.sakura.CleanDataApi.servicio.ColoniaServicio;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/colonias")
public class ColoniaControlador {

    private final ColoniaServicio coloniaServicio;

    public ColoniaControlador(ColoniaServicio coloniaServicio) {
        this.coloniaServicio = coloniaServicio;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Colonia> crear(@RequestBody Colonia colonia) {
        return ResponseEntity.ok(coloniaServicio.crearColonia(colonia));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    public ResponseEntity<ColoniaDTO> obtenerPorId(@PathVariable Integer id) {
        Colonia colonia = coloniaServicio.obtenerColoniaPorId(id);
        return ResponseEntity.ok(toDTO(colonia));
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<ColoniaDTO>> listarTodas() {
        List<ColoniaDTO> dtos = coloniaServicio.obtenerTodasColonia().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/cp/{cp}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    public ResponseEntity<List<ColoniaDTO>> buscarPorCodigoPostal(@PathVariable String cp) {
        List<ColoniaDTO> dtos = coloniaServicio.buscarPorCodigoPostal(cp).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Colonia> actualizar(@RequestBody Colonia colonia) {
        return ResponseEntity.ok(coloniaServicio.actualizarColonia(colonia));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Colonia> actualizarPorId(
            @PathVariable Integer id,
            @RequestBody Colonia datos
    ) {
        return ResponseEntity.ok(coloniaServicio.actualizarColoniaPorId(id, datos));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        coloniaServicio.eliminarColonia(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    public ResponseEntity<ColoniaDTO> buscarPorNombre(@RequestParam String nombre) {
        Colonia colonia = coloniaServicio.buscarPorNombreExacto(nombre);
        return ResponseEntity.ok(toDTO(colonia));
    }

    @GetMapping("/buscar/contiene")
    @PreAuthorize("hasAnyRole('ADMIN', 'CIUDADANO')")
    public ResponseEntity<List<ColoniaDTO>> buscarContiene(@RequestParam String q) {
        List<ColoniaDTO> dtos = coloniaServicio.buscarPorNombreContiene(q).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    private ColoniaDTO toDTO(Colonia colonia) {
        return new ColoniaDTO(
                colonia.getIdColonia(),
                colonia.getNombre(),
                colonia.getCodigoPostal(),
                colonia.getLatitud() != null ? colonia.getLatitud().doubleValue() : null,
                colonia.getLongitud() != null ? colonia.getLongitud().doubleValue() : null
        );
    }
}
