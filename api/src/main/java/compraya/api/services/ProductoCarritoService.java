package compraya.api.services;

import org.springframework.http.ResponseEntity;
import compraya.api.interfaces.IProductosCarritoService;
import compraya.api.models.ProductosCarritoModel;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import compraya.api.repositories.IProductosCarritoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductoCarritoService implements IProductosCarritoService{

    private final IProductosCarritoRepository productosCarritoRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductoCarritoService.class);

    @Autowired
    public ProductoCarritoService(IProductosCarritoRepository productosCarritoRepository) {
        this.productosCarritoRepository = productosCarritoRepository;
    }

    @Override
    public ResponseEntity<?> get() {
        try {
            return ResponseEntity.ok((ArrayList<ProductosCarritoModel>) productosCarritoRepository.findAll());
        } catch (Exception e) {
            logger.error("Error al obtener los detalles de los productos del carrito", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al obtener los detalles de los productos de carrito.\"}");
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id) {
        try {
            Optional<ProductosCarritoModel> productos_carrito = productosCarritoRepository.findById(id);
            if (productos_carrito.isPresent()) {
                return ResponseEntity.ok(productos_carrito.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Detalles de los productos de carrito no encontrados.\"}");
            }
        } catch (Exception e) {
            logger.error("Error al obtener los detalles de los productos de carrito con id " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al obtener los detalles de los productos de carrito.\"}");
        }
    }

    @Override
    public ResponseEntity<?> post(ProductosCarritoModel productos_carrito) {
        try {
            ProductosCarritoModel savedproductos_carrito = productosCarritoRepository.save(productos_carrito);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedproductos_carrito);
        } catch (Exception e) {
            logger.error("Error al guardar los detalles de los productos de carrito", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al guardar los detalles.\"}");
        }
    }

    @Override
    public ResponseEntity<?> put(ProductosCarritoModel productos_carrito, Long id) {
        if (productosCarritoRepository.existsById(id)) {
            productos_carrito.setId(id);
            try {
                ProductosCarritoModel updatedproductos_carrito = productosCarritoRepository.save(productos_carrito);
                return ResponseEntity.ok(updatedproductos_carrito);
            } catch (Exception e) {
                logger.error("Error al actualizar los detalles de los productos de carrito", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al actualizar los detalles de los productos de carrito.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Detalles de los prodcutos de carrito no encontrados.\"}");
        }
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        if (productosCarritoRepository.existsById(id)) {
            try {
                productosCarritoRepository.deleteById(id);
                return ResponseEntity.ok("{\"message\": \"Detalles de los productos de carrito eliminados.\"}");
            } catch (Exception e) {
                logger.error("Error al eliminar los detalles de los productos de carrito", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"Error al eliminar los detalles de los productos de carrito.\"}");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"Detalles de los productos de carrito no encontrados.\"}");
        }
    }

    
}





