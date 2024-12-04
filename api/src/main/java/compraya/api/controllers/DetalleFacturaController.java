package compraya.api.controllers;

import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/detalle-facturas")
public class DetalleFacturaController {
    
    private final DetalleFacturaService detalleFacturaService;

    public DetalleFacturaController(DetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getfacturas() {
        return detalleFacturaService.get();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createfactura(@RequestBody DetalleFacturaModel factura) {
        return detalleFacturaService.post(factura);
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getfacturaById(@PathVariable("id") Long id) {
        return detalleFacturaService.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updatefactura(@RequestBody DetalleFacturaModel factura, @PathVariable("id") Long id) {
        return detalleFacturaService.put(factura, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deletefactura(@PathVariable("id") Long id) {
        return detalleFacturaService.delete(id);
    }
    
}

