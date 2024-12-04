package compraya.api.interfaces;

import org.springframework.http.ResponseEntity;
import compraya.api.models.ProductoModel;

public interface IProductoService {
    ResponseEntity<?> get();
    ResponseEntity<?> getOne(Long id);
    ResponseEntity<?> post(ProductoModel producto);
    ResponseEntity<?> put(ProductoModel producto, Long id);
    ResponseEntity<?> delete(Long id);
}
