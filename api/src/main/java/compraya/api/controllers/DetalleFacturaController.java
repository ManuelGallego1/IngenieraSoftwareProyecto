package compraya.api.controllers;

import compraya.api.facade.DetalleFacturaFacade;
import compraya.api.models.DetalleFacturaModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/detalles-factura")
public class DetalleFacturaController {

    private final DetalleFacturaFacade detalleFacturaFacade;

    public DetalleFacturaController(DetalleFacturaFacade detalleFacturaFacade) {
        this.detalleFacturaFacade = detalleFacturaFacade;
    }

    @GetMapping
    public ResponseEntity<?> getDetallesFactura() {
        return detalleFacturaFacade.getDetallesFactura();
    }

    @PostMapping
    public ResponseEntity<?> createDetalleFactura(@RequestBody DetalleFacturaModel detalleFactura) {
        return detalleFacturaFacade.createDetalleFactura(detalleFactura);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetalleFacturaById(@PathVariable Long id) {
        return detalleFacturaFacade.getDetalleFacturaById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDetalleFactura(@RequestBody DetalleFacturaModel detalleFactura, @PathVariable Long id) {
        return detalleFacturaFacade.updateDetalleFactura(detalleFactura, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDetalleFactura(@PathVariable Long id) {
        return detalleFacturaFacade.deleteDetalleFactura(id);
    }
}