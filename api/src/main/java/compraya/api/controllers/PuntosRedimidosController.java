package compraya.api.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import compraya.api.models.PuntosRedimidosModel;
import compraya.api.services.PuntosRedimidosService;

@RestController
@RequestMapping("/puntos-redimidos")
public class PuntosRedimidosController {

    private final PuntosRedimidosService puntosRedimidosService;

    public PuntosRedimidosController(PuntosRedimidosService puntosRedimidosService) {
        this.puntosRedimidosService = puntosRedimidosService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPuntosRedimidos() {
        return puntosRedimidosService.get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPuntosRedimidosById(@PathVariable Long id) {
        return puntosRedimidosService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<?> createPuntosRedimidos(@RequestBody PuntosRedimidosModel puntos) {
        return puntosRedimidosService.post(puntos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePuntosRedimidos(@RequestBody PuntosRedimidosModel puntos, @PathVariable Long id) {
        return puntosRedimidosService.put(puntos, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePuntosRedimidos(@PathVariable Long id) {
        return puntosRedimidosService.delete(id);
    }
}