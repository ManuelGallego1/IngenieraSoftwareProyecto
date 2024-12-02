package compraya.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import compraya.api.models.FacturaModel;
import compraya.api.services.FacturaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    private final FacturaService FacturaService;

    @Autowired
    public FacturaController(FacturaService FacturaService) {
        this.FacturaService = FacturaService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getUsuarios() {
        return FacturaService.get();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createUsuario(@RequestBody FacturaModel usuario) {
        return FacturaService.post(usuario);
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getUsuarioById(@PathVariable("id") Long id) {
        return FacturaService.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateUsuario(@RequestBody FacturaModel usuario, @PathVariable("id") Long id) {
        return FacturaService.put(usuario, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") Long id) {
        return FacturaService.delete(id);
    }
}