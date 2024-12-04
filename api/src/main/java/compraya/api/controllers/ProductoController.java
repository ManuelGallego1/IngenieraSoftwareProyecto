package compraya.api.controllers;

import compraya.api.facade.ProductoFacade;
import compraya.api.models.ProductoModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoFacade productoFacade;

    public ProductoController(ProductoFacade productoFacade) {
        this.productoFacade = productoFacade;
    }

    @GetMapping
    public ResponseEntity<?> getProductos() {
        return productoFacade.getProductos();
    }

    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody ProductoModel producto) {
        return productoFacade.createProducto(producto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Long id) {
        return productoFacade.getProductoById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(@RequestBody ProductoModel producto, @PathVariable Long id) {
        return productoFacade.updateProducto(producto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        return productoFacade.deleteProducto(id);
    }
}