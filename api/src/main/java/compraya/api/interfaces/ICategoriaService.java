package compraya.api.interfaces;

import org.springframework.http.ResponseEntity;
import compraya.api.models.CategoriaModel;

public interface ICategoriaService {
    ResponseEntity<?> get();
    ResponseEntity<?> getOne(Long id);
    ResponseEntity<?> post(CategoriaModel categoria);
    ResponseEntity<?> put(CategoriaModel categoria, Long id);
    ResponseEntity<?> delete(Long id);
}
