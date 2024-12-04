package compraya.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import compraya.api.models.MetodoPagoModel;
import compraya.api.mediator.interfaces.MetodoPagoMediator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/metodo-pagos")
public class MetodoPagoController {

    private final MetodoPagoMediator metodoPagoMediator;

    public MetodoPagoController(@Qualifier("metodoPagoMediatorImpl") MetodoPagoMediator metodoPagoMediator) {
        this.metodoPagoMediator = metodoPagoMediator;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getMetodosPago() {
        return metodoPagoMediator.getMetodosPago();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createMetodoPago(@RequestBody MetodoPagoModel metodoPago) {
        return metodoPagoMediator.createMetodoPago(metodoPago);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getMetodoPagoById(@PathVariable("id") Long id) {
        return metodoPagoMediator.getMetodoPagoById(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateMetodoPago(@RequestBody MetodoPagoModel metodoPago, @PathVariable("id") Long id) {
        return metodoPagoMediator.updateMetodoPago(id, metodoPago);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteMetodoPago(@PathVariable("id") Long id) {
        return metodoPagoMediator.deleteMetodoPago(id);
    }
}