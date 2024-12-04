package compraya.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import compraya.api.models.ProductosCarritoModel;
import compraya.api.services.ProductoCarritoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping("/productos-carrito")
public class ProductoCarritoController {
    
    private final ProductoCarritoService ProductoCarritoService;

    public ProductoCarritoController(ProductoCarritoService ProductoCarritoService) {
        this.ProductoCarritoService = ProductoCarritoService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getproductos_carritos() {
        return ProductoCarritoService.get();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createproductos_carrito(@RequestBody ProductosCarritoModel productos_carrito) {
        return ProductoCarritoService.post(productos_carrito);
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getproductos_carritoById(@PathVariable("id") Long id) {
        return ProductoCarritoService.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateproductos_carrito(@RequestBody ProductosCarritoModel productos_carrito, @PathVariable("id") Long id) {
        return ProductoCarritoService.put(productos_carrito, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteproductos_carrito(@PathVariable("id") Long id) {
        return ProductoCarritoService.delete(id);
    }
    
}

