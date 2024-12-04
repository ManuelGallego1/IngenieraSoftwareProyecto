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
import compraya.api.models.ProductoModel;
import compraya.api.services.ProductosService;


@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductosService productosService;

    @Autowired
    public ProductoController(ProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getProductos() {
        return productosService.get();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createProducto(@RequestBody ProductoModel producto) {
        return productosService.post(producto);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> getProductoById(@PathVariable("id") Long id) {
        return productosService.getOne(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> updateProducto(@RequestBody ProductoModel producto, @PathVariable("id") Long id) {
        return productosService.put(producto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteProducto(@PathVariable("id") Long id) {
        return productosService.delete(id);
    }
}