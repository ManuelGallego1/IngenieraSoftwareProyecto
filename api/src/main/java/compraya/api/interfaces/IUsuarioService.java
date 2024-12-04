package compraya.api.interfaces;

import compraya.api.models.UsuarioModel;
import org.springframework.http.ResponseEntity;

public interface IUsuarioService {
    ResponseEntity<?> get();
    ResponseEntity<?> getOne(Long id);
    ResponseEntity<?> post(UsuarioModel usuario);
    ResponseEntity<?> put(UsuarioModel usuario, Long id);
    ResponseEntity<?> delete(Long id);
}