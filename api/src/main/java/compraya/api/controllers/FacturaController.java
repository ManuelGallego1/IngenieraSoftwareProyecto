package compraya.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import compraya.api.models.FacturaModel;
import compraya.api.mediator.interfaces.FacturaMediator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    private final FacturaMediator facturaMediator;

    public FacturaController(@Qualifier("facturaMediatorImpl") FacturaMediator facturaMediator) {
        this.facturaMediator = facturaMediator;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getFacturas() {
        return facturaMediator.getFacturas();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createFactura(@RequestBody FacturaModel factura) {
        return facturaMediator.createFactura(factura);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getFacturaById(@PathVariable("id") Long id) {
        return facturaMediator.getFacturaById(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateFactura(@RequestBody FacturaModel factura, @PathVariable("id") Long id) {
        return facturaMediator.updateFactura(factura, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteFactura(@PathVariable("id") Long id) {
        return facturaMediator.deleteFactura(id);
    }

    @GetMapping("/{id}/xml")
    @ResponseBody
    public ResponseEntity<?> generateFacturaXML(@PathVariable("id") Long id) {
        return facturaMediator.generateFacturaXML(id);
    }
}