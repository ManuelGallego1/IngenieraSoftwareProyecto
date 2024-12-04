package compraya.api.controllers;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import compraya.api.services.PuntosService;
import compraya.api.models.PuntosModel;


@RestController
@RequestMapping("/puntos")
public class PuntosController {

    private final PuntosService puntosService;

    @Autowired
    public PuntosController(PuntosService puntosService) {
        this.puntosService = puntosService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getPuntos() {
        return puntosService.get();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createPunto(@RequestBody PuntosModel punto) {
        return puntosService.post(punto);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getPuntoById(@PathVariable("id") Long id) {
        return puntosService.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updatePunto(@RequestBody PuntosModel punto, @PathVariable("id") Long id) {
        return puntosService.put(punto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletePunto(@PathVariable("id") Long id) {
        return puntosService.delete(id);
    }
}