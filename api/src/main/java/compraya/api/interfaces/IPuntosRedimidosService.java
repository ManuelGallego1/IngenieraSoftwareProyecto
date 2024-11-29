package compraya.api.interfaces;

import org.springframework.http.ResponseEntity;

import compraya.api.models.PuntosRedimidosModel;

public interface IPuntosRedimidosService {
    ResponseEntity<?> get();
    ResponseEntity<?> getOne(Long id);
    ResponseEntity<?> post(PuntosRedimidosModel puntos);
    ResponseEntity<?> put(PuntosRedimidosModel puntos, Long id);
    ResponseEntity<?> delete(Long id);
}
