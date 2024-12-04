package compraya.api.interfaces;

import org.springframework.http.ResponseEntity;
import compraya.api.models.PuntosGanadosModel;

public interface IPuntosGanadosService {
    ResponseEntity<?> get();
    ResponseEntity<?> getOne(Long id);
    ResponseEntity<?> post(PuntosGanadosModel puntos);
    ResponseEntity<?> put(PuntosGanadosModel puntos, Long id);
    ResponseEntity<?> delete(Long id);
}
