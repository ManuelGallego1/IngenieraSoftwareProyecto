package compraya.api.interfaces;

import org.springframework.http.ResponseEntity;
import compraya.api.models.CarritoModel;

public interface ICarritoService {
    ResponseEntity<?> get();
    ResponseEntity<?> getOne(Long id);
    ResponseEntity<?> post(CarritoModel carrito);
    ResponseEntity<?> put(CarritoModel carrito, Long id);
    ResponseEntity<?> delete(Long id);
}
