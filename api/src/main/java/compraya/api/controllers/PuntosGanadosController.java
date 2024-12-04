package compraya.api.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import compraya.api.models.PuntosGanadosModel;
import compraya.api.services.PuntosGanadosService;

@RestController
@RequestMapping("/puntos-ganados")
public class PuntosGanadosController {

    private final PuntosGanadosService puntosGanadosService;

    public PuntosGanadosController(PuntosGanadosService puntosGanadosService) {
        this.puntosGanadosService = puntosGanadosService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPuntosGanados() {
        return puntosGanadosService.get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPuntosGanadosById(@PathVariable Long id) {
        return puntosGanadosService.getOne(id);
    }

    @PostMapping
    public ResponseEntity<?> createPuntosGanados(@RequestBody PuntosGanadosModel puntos) {
        return puntosGanadosService.post(puntos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePuntosGanados(@RequestBody PuntosGanadosModel puntos, @PathVariable Long id) {
        return puntosGanadosService.put(puntos, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePuntosGanados(@PathVariable Long id) {
        return puntosGanadosService.delete(id);
    }
}