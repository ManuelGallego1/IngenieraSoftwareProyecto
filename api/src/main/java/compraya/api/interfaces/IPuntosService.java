package compraya.api.interfaces;

import org.springframework.http.ResponseEntity;
import compraya.api.models.PuntosModel;

public interface IPuntosService {
    ResponseEntity<?> get();
    ResponseEntity<?> getOne(Long id);
    ResponseEntity<?> post(PuntosModel puntos);
    ResponseEntity<?> put(PuntosModel puntos, Long id);
    ResponseEntity<?> delete(Long id);
}
