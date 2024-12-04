package compraya.api.services;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import compraya.api.interfaces.IProductoService;
import compraya.api.models.ProductoModel;
import compraya.api.repositories.IProductoRepository;

@Service
public class ProductosService implements IProductoService {

    private final IProductoRepository productoRepository;

    @Autowired
    public ProductosService(IProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        return ResponseEntity.ok((ArrayList<ProductoModel>) productoRepository.findAll());
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        Optional<ProductoModel> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Producto no encontrado.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(ProductoModel producto) {
        try {
            ProductoModel savedProducto = productoRepository.save(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProducto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al guardar el producto.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(ProductoModel producto, Long id) {
        if (productoRepository.existsById(id)) {
            producto.setId(id);
            try {
                ProductoModel updatedProducto = productoRepository.save(producto);
                return ResponseEntity.ok(updatedProducto);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al actualizar el producto.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Producto no encontrado.\"}");
        }
    }

    
    public ResponseEntity<?> delete(Long id) {
        if (productoRepository.existsById(id)) {
            try {
                productoRepository.deleteById(id);
                return ResponseEntity.ok("{\"message\": \"Producto eliminado.\"}");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al eliminar el producto.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Producto no encontrado.\"}");
        }
    }
}