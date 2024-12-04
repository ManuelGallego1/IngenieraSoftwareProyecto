package compraya.api.services;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import compraya.api.interfaces.IProductoService;
import compraya.api.models.ProductoModel;
import compraya.api.repositories.IProductoRepository;
import compraya.api.factories.interfaces.IProductoFactory;
import compraya.api.factories.DefaultProductoFactory;

@Service
public class ProductosService implements IProductoService {

    private final IProductoRepository productoRepository;
    private final IProductoFactory productoFactory = new DefaultProductoFactory();

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
            ProductoModel newProducto = productoFactory.createProducto();
            newProducto.setNombre(producto.getNombre());
            newProducto.setDescripcion(producto.getDescripcion());
            newProducto.setPrecio(producto.getPrecio());
            newProducto.setImagen(producto.getImagen());
            newProducto.setCategoria(producto.getCategoria());
            productoRepository.save(newProducto);
            return ResponseEntity.ok("{\"message\": \"Producto Creado.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"Error al guardar el producto.\"}");
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
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("{\"error\": \"Error al actualizar el producto.\"}");
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
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("{\"error\": \"Error al eliminar el producto.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Producto no encontrado.\"}");
        }
    }
}