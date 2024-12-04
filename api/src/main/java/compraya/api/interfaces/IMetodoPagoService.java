package compraya.api.interfaces;

import org.springframework.http.ResponseEntity;
import compraya.api.models.MetodoPagoModel;

public interface IMetodoPagoService {
    ResponseEntity<?> get();
    ResponseEntity<?> getOne(Long id);
    ResponseEntity<?> post(MetodoPagoModel metodoPago);
    ResponseEntity<?> put(MetodoPagoModel metodoPago, Long id);
    ResponseEntity<?> delete(Long id);
}
