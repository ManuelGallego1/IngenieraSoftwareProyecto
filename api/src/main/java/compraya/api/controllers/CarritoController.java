package compraya.api.controllers;

import compraya.api.facade.CarritoFacade;
import compraya.api.models.CarritoModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carritos")
public class CarritoController {

    private final CarritoFacade carritoFacade;

    public CarritoController(CarritoFacade carritoFacade) {
        this.carritoFacade = carritoFacade;
    }

    @GetMapping
    public ResponseEntity<?> getCarritos() {
        return carritoFacade.getCarritos();
    }

    @PostMapping
    public ResponseEntity<?> createCarrito(@RequestBody CarritoModel carrito) {
        return carritoFacade.createCarrito(carrito);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarritoById(@PathVariable Long id) {
        return carritoFacade.getCarritoById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCarrito(@RequestBody CarritoModel carrito, @PathVariable Long id) {
        return carritoFacade.updateCarrito(carrito, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarrito(@PathVariable Long id) {
        return carritoFacade.deleteCarrito(id);
    }
}