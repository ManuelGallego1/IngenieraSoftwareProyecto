package compraya.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import compraya.api.models.DetalleFacturaModel;
import compraya.api.services.DetalleFacturaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/detalle-factura")
public class DetalleFacturaController {
    
    private final DetalleFacturaService detalleFacturaService;

    @Autowired
    public DetalleFacturaController(DetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getUsuarios() {
        return detalleFacturaService.get();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createUsuario(@RequestBody DetalleFacturaModel usuario) {
        return detalleFacturaService.post(usuario);
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getUsuarioById(@PathVariable("id") Long id) {
        return detalleFacturaService.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateUsuario(@RequestBody DetalleFacturaModel usuario, @PathVariable("id") Long id) {
        return detalleFacturaService.put(usuario, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteUsuario(@PathVariable("id") Long id) {
        return detalleFacturaService.delete(id);
    }
    
}

