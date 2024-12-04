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
import compraya.api.models.CarritoModel;
import compraya.api.services.CarritoService;


@RestController
@RequestMapping("/carritos")
public class CarritoController {

    private final CarritoService carritoService;

    @Autowired
    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getCarritos() {
        return carritoService.get();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createCarrito(@RequestBody CarritoModel carrito) {
        return carritoService.post(carrito);
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getCarritoById(@PathVariable("id") Long id) {
        return carritoService.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateCarrito(@RequestBody CarritoModel carrito, @PathVariable("id") Long id) {
        return carritoService.put(carrito, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteCarrito(@PathVariable("id") Long id) {
        return carritoService.delete(id);
    }
}